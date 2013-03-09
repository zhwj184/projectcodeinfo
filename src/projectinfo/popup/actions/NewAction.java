package projectinfo.popup.actions;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewAction implements IObjectActionDelegate {

	private Shell shell;
	
	private ISelection selection;
	
	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		System.out.println("**********************工程项目代码统计****************************");
		if(action != null){
			File file = getSelectedFile(selection);
			String fileName = file.getAbsolutePath();
			CountCodeLines.getCodeLines(fileName);
			int codeLines = CountCodeLines.codeLines;
			int whiteLines = CountCodeLines.whiteLines;
			int commentLines = CountCodeLines.commentLines;
			int classNumber = CountCodeLines.classNumber;
			MessageDialog.openInformation(
					shell,
					"ClasNumber:" + classNumber,
					"codeLines:" + codeLines + "\nwhiteLines:" + whiteLines + "\ncomment:" + commentLines);	
		}
	}
	

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		StructuredSelection ss = (StructuredSelection) selection;
		
	}
	
	
	
	
	public File getSelectedFile(ISelection selection) {
        if (!(selection instanceof StructuredSelection)) {
            return null;
        }
        StructuredSelection sel = (StructuredSelection) selection;
        if (sel.size() != 1) {
            return null;
        }
        Object object = sel.getFirstElement();
        IResource resource = null;
        if (object instanceof IResource) {
            resource = (IResource) object;
        } else if (object instanceof IAdaptable) {
            IAdaptable adaptable = (IAdaptable) object;
            resource = (IResource) adaptable.getAdapter(org.eclipse.core.resources.IResource.class);
            if (resource instanceof IFile) {
                return ((IFile) resource).getLocation().toFile();
            }
        }
        if (resource == null) {
            return null;
        }
        if (resource.getLocation() != null) {
            return resource.getLocation().toFile();
        } else {
            return null;
        }
    }

}
