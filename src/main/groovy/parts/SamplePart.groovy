/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package parts

import javax.annotation.PostConstruct
import org.eclipse.e4.ui.di.Focus
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label

class SamplePart {

	private Label label
	private TableViewer tableViewer

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.layout = new GridLayout()

		label = new Label(parent, SWT.NONE)
		label.text = "Sample table"

		tableViewer = new TableViewer(parent)
		tableViewer.add("Sample item 1")
		tableViewer.add("Sample item 2")
		tableViewer.add("Sample item 3")
		tableViewer.add("Sample item 4")
		tableViewer.add("Sample item 5")
		tableViewer.table.layoutData = new GridData(GridData.FILL_BOTH)
	}

	@Focus
	public void setFocus() {
		tableViewer.table.setFocus()
	}
}
