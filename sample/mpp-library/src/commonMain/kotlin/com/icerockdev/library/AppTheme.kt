/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library

import com.icerockdev.library.sample.CryptoProfileScreen
import com.icerockdev.library.sample.UsersScreen
import com.icerockdev.library.universal.LoginScreen
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.widgets.ButtonWidget
import dev.icerock.moko.widgets.CollectionWidget
import dev.icerock.moko.widgets.ConstraintWidget
import dev.icerock.moko.widgets.ImageWidget
import dev.icerock.moko.widgets.InputWidget
import dev.icerock.moko.widgets.StatefulWidget
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.factory.DefaultCollectionWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultCollectionWidgetViewFactoryBase
import dev.icerock.moko.widgets.factory.DefaultConstraintWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultConstraintWidgetViewFactoryBase
import dev.icerock.moko.widgets.factory.DefaultImageWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultImageWidgetViewFactoryBase
import dev.icerock.moko.widgets.factory.DefaultInputWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultInputWidgetViewFactoryBase
import dev.icerock.moko.widgets.factory.DefaultListWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultListWidgetViewFactoryBase
import dev.icerock.moko.widgets.factory.SimpleStatefulViewFactory
import dev.icerock.moko.widgets.factory.SystemButtonViewFactory
import dev.icerock.moko.widgets.factory.SystemTextViewFactory
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.background.Border
import dev.icerock.moko.widgets.style.background.Fill
import dev.icerock.moko.widgets.style.background.Shape
import dev.icerock.moko.widgets.style.background.StateBackground
import dev.icerock.moko.widgets.style.view.Colors
import dev.icerock.moko.widgets.style.view.MarginValues
import dev.icerock.moko.widgets.style.view.PaddingValues
import dev.icerock.moko.widgets.style.view.TextAlignment
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.rgba
import dev.icerock.moko.widgets.utils.platformSpecific

object AppTheme {
    object PostsCollection : CollectionWidget.Category

    object AppColor {
        val white = Color(0xFF, 0xFF, 0xFF, 0xFF)
        val redError = Color(0xFF, 0x66, 0x66, 0xFF)
        val grayText = Color(0x26, 0x26, 0x28, 0xFF)
        val gray2Text = Color(0x4A, 0x4A, 0x4A, 0xFF)
        val lightGrayText = rgba(38, 38, 40, 0.4)
        val focusedGrayUnderline = Color(0xDD, 0xDD, 0xDD, 0xFF)
        val grayUnderline = Color(0xAA, 0xAA, 0xAA, 0xFF)
    }

    val baseTheme = Theme {
        factory[CryptoProfileScreen.Id.DelimiterText] = SystemTextViewFactory(
            textAlignment = TextAlignment.CENTER
        )
        factory[UsersScreen.Id.List] = DefaultListWidgetViewFactory(
            DefaultListWidgetViewFactoryBase.Style(
                padding = PaddingValues(8f)
            )
        )

        factory[StatefulWidget.DefaultCategory] = SimpleStatefulViewFactory(
            padding = PaddingValues(16f)
        )
        factory[CryptoProfileScreen.Id.JoinButton] = SystemButtonViewFactory(
            background = {
                val normalBackground = Background(
                    fill = Fill.Solid(color = Color(0x1375F8FF)),
                    shape = Shape.Rectangle(cornerRadius = 22f)
                )
                val disabledBackground = normalBackground.copy(
                    fill = Fill.Solid(color = Color(0x1375F880))
                )
                val pressedBackground = normalBackground.copy(
                    fill = Fill.Solid(color = Color(0x1375F8BB))
                )
                StateBackground(
                    normal = normalBackground,
                    disabled = disabledBackground,
                    pressed = pressedBackground
                )
            }()
        )
        factory[CryptoProfileScreen.Id.TryDemoButton] = SystemButtonViewFactory(
            background = {
                val normalBackground = Background(
                    fill = Fill.Solid(color = Color(0x303030FF)),
                    shape = Shape.Rectangle(cornerRadius = 22f),
                    border = Border(
                        color = Colors.white,
                        width = 1f
                    )
                )
                val disabledBackground = normalBackground.copy(
                    fill = Fill.Solid(color = Color(0x30303080))
                )
                val pressedBackground = normalBackground.copy(
                    fill = Fill.Solid(color = Color(0x303030BB))
                )
                StateBackground(
                    normal = normalBackground,
                    disabled = disabledBackground,
                    pressed = pressedBackground
                )
            }()
        )

        factory[PostsCollection] = DefaultCollectionWidgetViewFactory(
            DefaultCollectionWidgetViewFactoryBase.Style(
                padding = PaddingValues(4f)
            )
        )
    }

    val loginScreen = Theme(baseTheme) {
        factory[ConstraintWidget.DefaultCategory] = DefaultConstraintWidgetViewFactory(
            DefaultConstraintWidgetViewFactoryBase.Style(
                padding = PaddingValues(16f),
                background = Background(
                    fill = Fill.Solid(Colors.white)
                )
            )
        )

        factory[ImageWidget.DefaultCategory] = DefaultImageWidgetViewFactory(
            DefaultImageWidgetViewFactoryBase.Style(
                scaleType = DefaultImageWidgetViewFactoryBase.ScaleType.FIT
            )
        )

        factory[InputWidget.DefaultCategory] = DefaultInputWidgetViewFactory(
            DefaultInputWidgetViewFactoryBase.Style(
                margins = MarginValues(bottom = 8f),
                underLineColor = Color(0xe5e6eeFF),
                labelTextStyle = TextStyle(
                    color = Color(0x777889FF)
                )
            )
        )

        val corners = platformSpecific(android = 8f, ios = 25f)

        factory[ButtonWidget.DefaultCategory] = SystemButtonViewFactory(
            margins = MarginValues(top = 32f),
            background = {
                val bg: (Color) -> Background = {
                    Background(
                        fill = Fill.Solid(it),
                        shape = Shape.Rectangle(
                            cornerRadius = corners
                        )
                    )
                }
                StateBackground(
                    normal = bg(Color(0x6770e0FF)),
                    pressed = bg(Color(0x6770e0EE)),
                    disabled = bg(Color(0x6770e0BB))
                )
            }.invoke(),
            textStyle = TextStyle(
                color = Colors.white
            )
        )

        factory[LoginScreen.Id.RegistrationButtonId] = SystemButtonViewFactory(
            margins = MarginValues(top = 16f),
            padding = platformSpecific(
                ios = PaddingValues(start = 16f, end = 16f),
                android = null
            ),
            background = {
                val bg: (Color) -> Background = {
                    Background(
                        fill = Fill.Solid(it),
                        border = Border(
                            color = Color(0xF2F2F8FF),
                            width = 2f
                        ),
                        shape = Shape.Rectangle(cornerRadius = corners)
                    )
                }
                StateBackground(
                    normal = bg(Colors.white),
                    pressed = bg(Color(0xEEEEEEFF)),
                    disabled = bg(Color(0xBBBBBBFF))
                )
            }.invoke(),
            textStyle = TextStyle(
                color = Color(0x777889FF)
            )
        )
    }


//    val errorTextStyle: TextStyle = TextStyle(
//        size = 12,
//        color = Color.redError,
//        fontStyle = FontStyle.MEDIUM // Roboto-regular weight 400
//    )
//
//    val inputStyle: InputWidget.Style = InputWidget.Style(
//        size = WidgetSize.Const(
//            width = SizeSpec.AsParent,
//            height = SizeSpec.WrapContent
//        ),
//        textStyle = TextStyle(
//            size = 15,
//            color = Color.grayText,
//            fontStyle = FontStyle.MEDIUM // Roboto-medium weight 500
//        ),
//        labelTextStyle = TextStyle(
//            size = 12,
//            color = Color.gray2Text,
//            fontStyle = FontStyle.MEDIUM // Roboto-regular weight 400
//        ),
//        errorTextStyle = errorTextStyle,
//        underLineColor = Color.grayUnderline,
//        underLineFocusedColor = Color.focusedGrayUnderline,
//        margins = MarginValues(
//            bottom = 8f
//        ),
//        background = null
//    )
//
//    val buttonStyle: ButtonWidget.Style = ButtonWidget.Style(
//        size = WidgetSize.Const(
//            width = SizeSpec.AsParent,
//            height = SizeSpec.Exact(50.0f)
//        ),
//        textStyle = TextStyle(
//            size = 17,
//            color = Color.white,
//            fontStyle = FontStyle.MEDIUM // Roboto-medium weight 500
//        ),
//        isAllCaps = false,
//        margins = MarginValues(),
//        background = {
//            val fill = Fill.Gradient(
//                colors = listOf(
//                    Color(0xB8, 0x3A, 0xF3, 0xFF),
//                    Color(0x69, 0x50, 0xFB, 0xFF)
//                ),
//                direction = Direction.TR_BL
//            )
//            val normalBackground = Background(
//                fill = fill,
//                shape = Shape.Rectangle(
//                    cornerRadius = platformSpecific(
//                        android = 16f,
//                        ios = 25f
//                    )
//                )
//            )
//            val pressedBackground = normalBackground.copy(
//                fill = fill.copy(
//                    colors = listOf(
//                        Color(0xB8, 0x3A, 0xF3, 0xBB),
//                        Color(0x69, 0x50, 0xFB, 0xBB)
//                    )
//                )
//            )
//            val disabledBackground = normalBackground.copy(
//                fill = fill.copy(
//                    colors = listOf(
//                        Color(0xB8, 0x3A, 0xF3, 0x80),
//                        Color(0x69, 0x50, 0xFB, 0x80)
//                    )
//                )
//            )
//
//            StateBackground(
//                normal = normalBackground,
//                disabled = disabledBackground,
//                pressed = pressedBackground
//            )
//        }()
//    )
//
//    val headerTextStyle: TextStyle = TextStyle(
//        size = 12,
//        color = Color.lightGrayText,
//        fontStyle = FontStyle.MEDIUM // Roboto-medium weight 500
//    )
//
//    val headerStyle: TextWidget.Style = TextWidget.Style(
//        textStyle = headerTextStyle,
//        padding = PaddingValues(),
//        margins = MarginValues(
//            bottom = 8f
//        )
//    )
//
//    val profileContainerStyle: LinearWidget.Style = LinearWidget.Style(
//        background = null,
//        orientation = Orientation.VERTICAL,
//        size = WidgetSize.Const(
//            width = SizeSpec.AsParent,
//            height = SizeSpec.WrapContent
//        ),
//        padding = PaddingValues(16f)
//    )
//
//    val singleChoiceStyle: SingleChoiceWidget.Style = SingleChoiceWidget.Style(
//        size = WidgetSize.Const(
//            width = SizeSpec.AsParent,
//            height = SizeSpec.WrapContent
//        ),
//        textStyle = TextStyle(
//            size = 15,
//            color = Color.grayText,
//            fontStyle = FontStyle.MEDIUM // Roboto-medium weight 500
//        ),
//        labelTextStyle = TextStyle(
//            size = 12,
//            color = Color.gray2Text,
//            fontStyle = FontStyle.MEDIUM // Roboto-regular weight 400
//        ),
//        margins = MarginValues(
//            bottom = 8f
//        ),
//        dropDownTextColor = null,
//        underlineColor = Color.grayUnderline,
//        dropDownBackground = null
//    )
//
//    val socialWidgetScope = Theme {
//        inputStyle = Theme.inputStyle
//        textStyle = Theme.headerStyle
//        buttonStyle = Theme.buttonStyle
//        linearStyle = Theme.profileContainerStyle
//        singleChoiceStyle = Theme.singleChoiceStyle
//
//        setLinearStyle(
//            linearStyle.copy(
//                padding = PaddingValues(),
//                margins = MarginValues(bottom = 8f)
//            ), SocialProfileScreen.Id.AgreementContainer
//        )
//    }
//
//    val mcommerceWidgetScope = Theme {
//        inputStyle = Theme.inputStyle.copy(
//            textStyle = TextStyle(
//                size = 16,
//                color = Color(0x4B, 0x4F, 0x4E, 0xFF),
//                fontStyle = FontStyle.MEDIUM // ProximaNova-Semibold weight 600
//            ),
//            labelTextStyle = TextStyle(
//                size = 12,
//                color = Color(0x99, 0x9E, 0x9C, 0xFF), // #999e9cff
//                fontStyle = FontStyle.MEDIUM // ProximaNova-Regular weight 400
//            ),
//            underLineColor = Color(0x00, 0x00, 0x00, 0x1F) // #0000001f
//        )
//        buttonStyle = Theme.buttonStyle.copy(
//            textStyle = TextStyle(
//                size = 17,
//                color = Color.white,
//                fontStyle = FontStyle.MEDIUM // Roboto-medium weight 500
//            ),
//            background = {
//                val normalBackground = Background(
//                    fill = Fill.Solid(color = Color(0x00, 0x49, 0x2C, 0xFF)),
//                    shape = Shape.Rectangle(cornerRadius = 4f)
//                )
//                val disabledBackground = normalBackground.copy(
//                    fill = Fill.Solid(color = Color(0x00, 0x49, 0x2C, 0x80))
//                )
//                val pressedBackground = normalBackground.copy(
//                    fill = Fill.Solid(color = Color(0x00, 0x49, 0x2C, 0xBB))
//                )
//                StateBackground(
//                    normal = normalBackground,
//                    disabled = disabledBackground,
//                    pressed = pressedBackground
//                )
//            }()
//        )
//        linearStyle = Theme.profileContainerStyle
//    }
//
//    val cryptoWidgetScope = Theme {
//        inputStyle = Theme.inputStyle.copy(
//            textStyle = TextStyle(
//                size = 16,
//                color = Colors.white, // #ffffffff
//                fontStyle = FontStyle.MEDIUM // Gotham Pro
//            ),
//            labelTextStyle = TextStyle(
//                size = 12,
//                color = Color(0xA6, 0xA6, 0xA6, 0xFF), // #a6a6a6ff
//                fontStyle = FontStyle.MEDIUM // Gotham Pro
//            ),
//            underLineColor = Colors.white.copy(alpha = 0xAA),
//            underLineFocusedColor = Colors.white
//        )
//        textStyle = Theme.headerStyle.copy(
//            textStyle = TextStyle(
//                size = 14,
//                color = Colors.white, // #ffffffff
//                fontStyle = FontStyle.MEDIUM // Gotham Pro
//            )
//        )
//        setTextStyle(
//            textStyle.copy(
//                textAlignment = TextAlignment.CENTER
//            ), CryptoProfileScreen.Id.DelimiterText
//        )
//        linearStyle = Theme.profileContainerStyle
//        scrollStyle = ScrollWidget.Style(
//            background = Background(
//                fill = Fill.Solid(Colors.black)
//            )
//        )
//        singleChoiceStyle = Theme.singleChoiceStyle
//    }
}
