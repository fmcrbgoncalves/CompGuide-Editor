package org.di.uminho.cguide.wizard.Edit.Condition;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditConditionWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditConditionWizard.class);

	public EditConditionPage1 editconditionpage1;

	public EditConditionPage2 editconditionpage2;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditConditionWizard(OWLEditorKit editorKit, String condition_individual) {
		setTitle("Condition Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditConditionPage1.ID,
				editconditionpage1 = new EditConditionPage1(editorKit, condition_individual));

		registerWizardPanel(EditConditionPage2.ID,
				editconditionpage2 = new EditConditionPage2(editorKit, condition_individual));

		setCurrentPanel(EditConditionPage1.ID);
	}

}
