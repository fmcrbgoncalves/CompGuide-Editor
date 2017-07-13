package org.di.uminho.cguide.wizard.Edit.Procedure;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditProcedureWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditProcedureWizard.class);

	public EditProcedurePage editprocedurepage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditProcedureWizard(OWLEditorKit editorKit, String procedure_individual) {
		setTitle("Procedure Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditProcedurePage.ID,
				editprocedurepage = new EditProcedurePage(editorKit, procedure_individual));

		setCurrentPanel(EditProcedurePage.ID);
	}

}
