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
package handlers

import java.lang.reflect.InvocationTargetException

import javax.inject.Named

import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.e4.core.contexts.ContextInjectionFactory
import org.eclipse.e4.core.contexts.IEclipseContext
import org.eclipse.e4.core.di.annotations.Execute
import org.eclipse.e4.core.di.annotations.CanExecute
import org.eclipse.e4.ui.model.application.MContribution
import org.eclipse.e4.ui.model.application.ui.MDirtyable
import org.eclipse.e4.ui.services.IServiceConstants
//import org.eclipse.e4.ui.workbench.Persist
import org.eclipse.jface.dialogs.ProgressMonitorDialog
import org.eclipse.jface.operation.IRunnableWithProgress
import org.eclipse.swt.widgets.Shell

class SaveHandler {
	@CanExecute
	boolean canExecute(
			@Named(IServiceConstants.ACTIVE_PART) MDirtyable dirtyable) {
		dirtyable?.dirty
	}

	@Execute
	void execute(
			IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			@Named(IServiceConstants.ACTIVE_PART) MContribution contribution)
			throws InvocationTargetException, InterruptedException {
		IEclipseContext pmContext = context.createChild()

  		ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell)
  		dialog.open()
  		dialog.run(true, true) { IProgressMonitor monitor ->
  			pmContext.set(IProgressMonitor.class.getName(), monitor)
  			Object clientObject = contribution?.object
//					ContextInjectionFactory.invoke(clientObject, Persist.class, //$NON-NLS-1$
//							pmContext, null)
  		}

		pmContext.dispose()
	}
}
