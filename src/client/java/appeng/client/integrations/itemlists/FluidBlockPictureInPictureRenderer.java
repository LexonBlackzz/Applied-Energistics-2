package appeng.client.integrations.itemlists;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.QuadInstance;

import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Quaternionf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

import appeng.client.render.CubeBuilder;

public class FluidBlockPictureInPictureRenderer
        extends PictureInPictureRenderer<FluidBlockPictureInPictureRenderer.State> {

    @Override
    public Class<State> getRenderStateClass() {
        return State.class;
    }

    @Override
    protected void renderToTexture(State renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
        var minecraft = Minecraft.getInstance();
        var fluidModel = minecraft.getModelManager().getFluidStateModelSet()
                .get(renderState.fluid.defaultFluidState());

        minecraft.gameRenderer.lighting().setupFor(Lighting.Entry.LEVEL);

        var fluidStack = new FluidStack(renderState.fluid, 1);
        var sprite = fluidModel.stillMaterial().sprite();
        var tintSource = fluidModel.fluidTintSource();
        var color = tintSource != null ? tintSource.colorAsStack(fluidStack) : -1;

        poseStack.pushPose();
        setupOrthographicProjection(poseStack);

        submitNodeCollector.submitCustomGeometry(poseStack, Sheets.translucentBlockItemSheet(), (pose, buffer) -> {
            var quadInstance = new QuadInstance();
            quadInstance.setColor(color);
            quadInstance.setLightCoords(LightCoordsUtil.FULL_BRIGHT);

            var builder = new CubeBuilder(quad -> buffer.putBakedQuad(pose, quad, quadInstance));
            builder.setTexture(sprite);
            builder.addCube(0, 0, 0, 16, 16, 16);
        });

        poseStack.popPose();
    }

    @Override
    protected float getTranslateY(int height, int guiScale) {
        return height / 2.0F;
    }

    @Override
    protected String getTextureLabel() {
        return "AE2 Fluid in GUI";
    }

    public record State(
            Matrix3x2f pose,
            int x0, int y0,
            int x1, int y1,
            ScreenRectangle bounds,
            @Nullable ScreenRectangle scissorArea,
            Fluid fluid) implements PictureInPictureRenderState {
        @Override
        public float scale() {
            return 16;
        }
    }

    private static void setupOrthographicProjection(PoseStack poseStack) {
        // Set up orthographic rendering for the block
        float angle = 36;
        float rotation = 45;

        poseStack.scale(1, 1, -1);
        poseStack.mulPose(new Quaternionf().rotationY(Mth.DEG_TO_RAD * -180));

        Quaternionf flip = new Quaternionf().rotationZ(Mth.DEG_TO_RAD * 180);
        flip.mul(new Quaternionf().rotationX(Mth.DEG_TO_RAD * angle));

        Quaternionf rotate = new Quaternionf().rotationY(Mth.DEG_TO_RAD * rotation);
        poseStack.mulPose(flip);
        poseStack.mulPose(rotate);

        // Move into the center of the block for the transforms
        poseStack.translate(-0.5f, -0.5f, -0.5f);
    }
}
