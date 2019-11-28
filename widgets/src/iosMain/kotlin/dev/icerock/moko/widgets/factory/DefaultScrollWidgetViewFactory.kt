/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.factory

import dev.icerock.moko.widgets.ScrollWidget
import dev.icerock.moko.widgets.core.ViewBundle
import dev.icerock.moko.widgets.core.ViewFactoryContext
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.utils.Edges
import dev.icerock.moko.widgets.utils.applyBackground
import dev.icerock.moko.widgets.utils.layoutWidget
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIScrollView
import platform.UIKit.UIScrollViewKeyboardDismissMode
import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.bottomAnchor
import platform.UIKit.leadingAnchor
import platform.UIKit.topAnchor
import platform.UIKit.translatesAutoresizingMaskIntoConstraints
import platform.UIKit.widthAnchor

actual class DefaultScrollWidgetViewFactory actual constructor(
    style: Style
) : DefaultScrollWidgetViewFactoryBase(style) {

    override fun <WS : WidgetSize> build(
        widget: ScrollWidget<out WidgetSize>,
        size: WS,
        viewFactoryContext: ViewFactoryContext
    ): ViewBundle<WS> {
        val viewController: UIViewController = viewFactoryContext

        val scrollView = UIScrollView(frame = CGRectZero.readValue()).apply {
            translatesAutoresizingMaskIntoConstraints = false
            alwaysBounceVertical = true
            keyboardDismissMode =
                UIScrollViewKeyboardDismissMode.UIScrollViewKeyboardDismissModeInteractive
            applyBackground(style.background)
        }

        val childBundle = widget.child.buildView(viewController)
        val childView = childBundle.view
        childView.translatesAutoresizingMaskIntoConstraints = false

        with(scrollView) {
            addSubview(childView)

            val edges: Edges<CGFloat> = layoutWidget(
                rootView = this,
                rootPadding = style.padding,
                childView = childView,
                childSize = childBundle.size,
                childMargins = childBundle.margins
            )

            childView.topAnchor.constraintEqualToAnchor(
                anchor = topAnchor,
                constant = edges.top
            ).active = true
            childView.leadingAnchor.constraintEqualToAnchor(
                anchor = leadingAnchor,
                constant = edges.leading
            ).active = true
            childView.widthAnchor.constraintEqualToAnchor(
                anchor = widthAnchor,
                constant = -(edges.trailing + edges.leading)
            ).active = true
            contentLayoutGuide.widthAnchor.constraintEqualToAnchor(
                anchor = childView.widthAnchor
            ).active = true
            contentLayoutGuide.bottomAnchor.constraintEqualToAnchor(
                anchor = childView.bottomAnchor,
                constant = edges.bottom
            ).active = true
        }

        return ViewBundle(
            view = scrollView,
            size = size,
            margins = style.margins
        )
    }
}