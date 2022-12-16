package io.github.palexdev.materialfx.controls;

import io.github.palexdev.materialfx.MFXResourcesLoader;
import io.github.palexdev.materialfx.beans.PositionBean;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.effects.ripple.MFXCircleRippleGenerator;
import io.github.palexdev.materialfx.enums.ButtonType;
import io.github.palexdev.materialfx.skins.MFXClassicToggleButtonSkin;
import io.github.palexdev.materialfx.utils.StyleablePropertiesUtils;
import javafx.beans.property.*;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;

public class MFXClassicToggleButton extends ToggleButton
{
    //================================================================================
    // Properties
    //================================================================================
    private static final StyleablePropertyFactory<MFXClassicToggleButton> FACTORY = new StyleablePropertyFactory<>(ToggleButton.getClassCssMetaData());
    private final String STYLE_CLASS = "mfx-classic-toggle-button";
    private final String STYLESHEET = MFXResourcesLoader.load("css/MFXClassicToggleButton.css");
    private final MFXCircleRippleGenerator rippleGenerator = new MFXCircleRippleGenerator(this);

    //================================================================================
    // Constructors
    //================================================================================
    public MFXClassicToggleButton()
    {
        setText("Button");
        initialize();
    }

    public MFXClassicToggleButton(String text)
    {
        super(text);
        initialize();
    }

    public MFXClassicToggleButton(String text, double prefWidth, double prefHeight)
    {
        super(text);
        setPrefSize(prefWidth, prefHeight);
        initialize();
    }

    public MFXClassicToggleButton(String text, Node graphic)
    {
        super(text, graphic);
        initialize();
    }

    //================================================================================
    // Methods
    //================================================================================
    private void initialize()
    {
        getStyleClass().add(STYLE_CLASS);
        setAlignment(Pos.CENTER);
        setupRippleGenerator();
    }

    public MFXCircleRippleGenerator getRippleGenerator()
    {
        return this.rippleGenerator;
    }

    //================================================================================
    // Ripple properties
    //================================================================================
    private final BooleanProperty computeRadiusMultiplier = new SimpleBooleanProperty(rippleGenerator.isComputeRadiusMultiplier());
    private final BooleanProperty rippleAnimateBackground = new SimpleBooleanProperty(rippleGenerator.isAnimateBackground());
    private final BooleanProperty rippleAnimateShadow = new SimpleBooleanProperty(rippleGenerator.isAnimateShadow());
    private final DoubleProperty rippleAnimationSpeed = new SimpleDoubleProperty(rippleGenerator.getAnimationSpeed());
    private final DoubleProperty rippleBackgroundOpacity = new SimpleDoubleProperty(rippleGenerator.getBackgroundOpacity());
    private final ObjectProperty<Paint> rippleColor = new SimpleObjectProperty<>(rippleGenerator.getRippleColor());
    private final DoubleProperty rippleRadius = new SimpleDoubleProperty(rippleGenerator.getRippleRadius());
    private final DoubleProperty rippleRadiusMultiplier = new SimpleDoubleProperty(rippleGenerator.getRadiusMultiplier());

    /**
     * Binds the button's ripple properties to the ripple generator ones.
     */
    protected void setupRippleGenerator()
    {
        MFXCircleRippleGenerator rippleGenerator = getRippleGenerator();

        rippleGenerator.rippleColorProperty().bindBidirectional(rippleColorProperty());
        rippleGenerator.rippleRadiusProperty().bindBidirectional(rippleRadiusProperty());
        rippleGenerator.animationSpeedProperty().bindBidirectional(rippleAnimationSpeedProperty());
        rippleGenerator.backgroundOpacityProperty().bindBidirectional(rippleBackgroundOpacityProperty());
        rippleGenerator.radiusMultiplierProperty().bind(rippleRadiusMultiplierProperty());
        rippleGenerator.computeRadiusMultiplierProperty().bind(computeRadiusMultiplierProperty());
        rippleGenerator.animateBackgroundProperty().bind(rippleAnimateBackgroundProperty());
        rippleGenerator.animateShadowProperty().bind(rippleAnimateShadowProperty());

        setRippleColor(Color.rgb(190, 190, 190));
        setRippleRadius(25);
        setComputeRadiusMultiplier(true);
        rippleGenerator.setRipplePositionFunction(event -> PositionBean.of(event.getX(), event.getY()));
    }

    public boolean isComputeRadiusMultiplier()
    {
        return computeRadiusMultiplier.get();
    }

    /**
     * Specifies if the {@link #rippleRadiusMultiplierProperty()} should be computed automatically.
     *
     * @see MFXCircleRippleGenerator
     */
    public BooleanProperty computeRadiusMultiplierProperty()
    {
        return computeRadiusMultiplier;
    }

    public void setComputeRadiusMultiplier(boolean computeRadiusMultiplier)
    {
        this.computeRadiusMultiplier.set(computeRadiusMultiplier);
    }

    public boolean isRippleAnimateBackground()
    {
        return rippleAnimateBackground.get();
    }

    /**
     * Specifies if the button's background should also be animated.
     */
    public BooleanProperty rippleAnimateBackgroundProperty()
    {
        return rippleAnimateBackground;
    }

    public void setRippleAnimateBackground(boolean rippleAnimateBackground)
    {
        this.rippleAnimateBackground.set(rippleAnimateBackground);
    }

    public boolean isRippleAnimateShadow()
    {
        return rippleAnimateShadow.get();
    }

    /**
     * Specifies if the button's shadow should also be animated.
     */
    public BooleanProperty rippleAnimateShadowProperty()
    {
        return rippleAnimateShadow;
    }

    public void setRippleAnimateShadow(boolean rippleAnimateShadow)
    {
        this.rippleAnimateShadow.set(rippleAnimateShadow);
    }

    public double getRippleAnimationSpeed()
    {
        return rippleAnimationSpeed.get();
    }

    /**
     * Specifies the ripple generator's animations speed.
     */
    public DoubleProperty rippleAnimationSpeedProperty()
    {
        return rippleAnimationSpeed;
    }

    public void setRippleAnimationSpeed(double rippleAnimationSpeed)
    {
        this.rippleAnimationSpeed.set(rippleAnimationSpeed);
    }

    public double getRippleBackgroundOpacity()
    {
        return rippleBackgroundOpacity.get();
    }

    /**
     * Specifies the opacity for the background animation. (if {@link #rippleAnimateBackgroundProperty()} is true).
     */
    public DoubleProperty rippleBackgroundOpacityProperty()
    {
        return rippleBackgroundOpacity;
    }

    public void setRippleBackgroundOpacity(double rippleBackgroundOpacity)
    {
        this.rippleBackgroundOpacity.set(rippleBackgroundOpacity);
    }

    public final Paint getRippleColor()
    {
        return rippleColor.get();
    }

    /**
     * Specifies the ripples color of this control.
     */
    public final ObjectProperty<Paint> rippleColorProperty()
    {
        return this.rippleColor;
    }

    public final void setRippleColor(Paint rippleColor)
    {
        this.rippleColor.set(rippleColor);
    }

    public double getRippleRadius()
    {
        return rippleRadius.get();
    }

    /**
     * Specifies the radius of the ripples.
     */
    public DoubleProperty rippleRadiusProperty()
    {
        return rippleRadius;
    }

    public void setRippleRadius(double rippleRadius)
    {
        this.rippleRadius.set(rippleRadius);
    }

    public double getRippleRadiusMultiplier()
    {
        return rippleRadiusMultiplier.get();
    }

    /**
     * Specifies the number by which the ripples' radius will be multiplied.
     *
     * @see MFXCircleRippleGenerator
     */
    public DoubleProperty rippleRadiusMultiplierProperty()
    {
        return rippleRadiusMultiplier;
    }

    public void setRippleRadiusMultiplier(double rippleRadiusMultiplier)
    {
        this.rippleRadiusMultiplier.set(rippleRadiusMultiplier);
    }

    //================================================================================
    // Styleable Properties
    //================================================================================
    private final StyleableObjectProperty<DepthLevel> depthLevel = new SimpleStyleableObjectProperty<>(
            MFXClassicToggleButton.StyleableProperties.DEPTH_LEVEL,
            this,
            "depthLevel",
            DepthLevel.LEVEL2
    );

    private final StyleableObjectProperty<ButtonType> buttonType = new SimpleStyleableObjectProperty<>(
            MFXClassicToggleButton.StyleableProperties.BUTTON_TYPE,
            this,
            "buttonType",
            ButtonType.FLAT
    )
    {
        @Override
        public void set(ButtonType v)
        {
            if (v == ButtonType.FLAT)
            {
                setEffect(null);
            }
            super.set(v);
        }
    };

    public DepthLevel getDepthLevel()
    {
        return depthLevel.get();
    }

    /**
     * Specifies how intense is the {@code DropShadow} effect applied to this control.
     * <p>
     * The {@code DropShadow} effect is used to make the control appear {@code RAISED}.
     *
     * @see io.github.palexdev.materialfx.effects.MFXDepthManager
     */
    public StyleableObjectProperty<DepthLevel> depthLevelProperty()
    {
        return depthLevel;
    }

    public void setDepthLevel(DepthLevel depthLevel)
    {
        this.depthLevel.set(depthLevel);
    }

    public ButtonType getButtonType()
    {
        return buttonType.get();
    }

    /**
     * Specifies the appearance of this control. According to material design there are two types of buttons:
     * <p>
     * - {@code FLAT}
     * <p>
     * - {@code RAISED}
     * <p></p>
     * If the new type is {@code FLAT} then setEffect(null) is called so that a user can also
     * specify it's own effects for the button.
     */
    public StyleableObjectProperty<ButtonType> buttonTypeProperty()
    {
        return buttonType;
    }

    public void setButtonType(ButtonType buttonType)
    {
        this.buttonType.set(buttonType);
    }

    //================================================================================
    // CSSMetaData
    //================================================================================
    private static class StyleableProperties
    {
        private static final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;

        private static final CssMetaData<MFXClassicToggleButton, DepthLevel> DEPTH_LEVEL =
                FACTORY.createEnumCssMetaData(
                        DepthLevel.class,
                        "-mfx-depth-level",
                        MFXClassicToggleButton::depthLevelProperty,
                        DepthLevel.LEVEL2
                );

        private static final CssMetaData<MFXClassicToggleButton, ButtonType> BUTTON_TYPE =
                FACTORY.createEnumCssMetaData(
                        ButtonType.class,
                        "-mfx-button-type",
                        MFXClassicToggleButton::buttonTypeProperty,
                        ButtonType.FLAT);

        static
        {
            cssMetaDataList = StyleablePropertiesUtils.cssMetaDataList(
                    ToggleButton.getClassCssMetaData(),
                    DEPTH_LEVEL, BUTTON_TYPE
            );
        }

    }

    public static List<CssMetaData<? extends Styleable, ?>> getControlCssMetaDataList()
    {
        return MFXClassicToggleButton.StyleableProperties.cssMetaDataList;
    }

    //================================================================================
    // Override Methods
    //================================================================================
    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new MFXClassicToggleButtonSkin(this);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData()
    {
        return MFXClassicToggleButton.getControlCssMetaDataList();
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return STYLESHEET;
    }
}
