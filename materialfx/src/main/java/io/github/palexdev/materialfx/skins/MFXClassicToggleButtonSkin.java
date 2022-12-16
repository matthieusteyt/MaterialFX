package io.github.palexdev.materialfx.skins;

import io.github.palexdev.materialfx.controls.MFXClassicToggleButton;
import io.github.palexdev.materialfx.effects.MFXDepthManager;
import io.github.palexdev.materialfx.effects.ripple.MFXCircleRippleGenerator;
import javafx.scene.control.skin.ToggleButtonSkin;
import javafx.scene.input.MouseEvent;

public class MFXClassicToggleButtonSkin extends ToggleButtonSkin
{
    //================================================================================
    // Constructors
    //================================================================================
    public MFXClassicToggleButtonSkin(MFXClassicToggleButton button) {
        super(button);

        setListeners();
        updateButtonType();

        updateChildren();
    }

    //================================================================================
    // Methods
    //================================================================================

    /**
     * Adds listeners to: depthLevel and buttonType properties.
     */
    private void setListeners() {
        MFXClassicToggleButton button = (MFXClassicToggleButton) getSkinnable();
        MFXCircleRippleGenerator rippleGenerator = button.getRippleGenerator();

        button.depthLevelProperty().addListener((observable, oldValue, newValue) -> updateButtonType());
        button.buttonTypeProperty().addListener((observable, oldValue, newValue) -> updateButtonType());

        button.addEventFilter(MouseEvent.MOUSE_PRESSED, rippleGenerator::generateRipple);
    }

    /**
     * Changes the button type.
     */
    private void updateButtonType() {
        MFXClassicToggleButton button = (MFXClassicToggleButton) getSkinnable();

        switch (button.getButtonType()) {
            case RAISED: {
                button.setEffect(MFXDepthManager.shadowOf(button.getDepthLevel()));
                button.setPickOnBounds(false);
                break;
            }
            case FLAT: {
                button.setPickOnBounds(true);
                break;
            }
        }
    }

    //================================================================================
    // Override Methods
    //================================================================================
    @Override
    protected void updateChildren() {
        super.updateChildren();

        MFXClassicToggleButton button = (MFXClassicToggleButton) getSkinnable();
        if (!getChildren().contains(button.getRippleGenerator())) {
            getChildren().add(0, button.getRippleGenerator());
        }
    }
}