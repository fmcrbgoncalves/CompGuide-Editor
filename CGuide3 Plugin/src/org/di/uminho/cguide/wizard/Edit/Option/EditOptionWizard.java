package org.di.uminho.cguide.wizard.Edit.Option;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditOptionWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditOptionWizard.class);

	public EditOptionPage2 editoptionpage2;

	public EditOptionPage3 editoptionpage3;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditOptionWizard(OWLEditorKit editorKit, String option_individual) {
		setTitle("Option Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditOptionPage2.ID, editoptionpage2 = new EditOptionPage2(editorKit, option_individual));
		registerWizardPanel(EditOptionPage3.ID, editoptionpage3 = new EditOptionPage3(editorKit, option_individual));

		setCurrentPanel(EditOptionPage2.ID);
	}

}
