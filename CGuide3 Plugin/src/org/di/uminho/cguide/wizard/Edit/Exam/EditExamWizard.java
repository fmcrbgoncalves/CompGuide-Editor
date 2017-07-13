package org.di.uminho.cguide.wizard.Edit.Exam;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditExamWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditExamWizard.class);

	public EditExamPage editexampage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditExamWizard(OWLEditorKit editorKit, String exam_individual) {
		setTitle("Exam Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditExamPage.ID, editexampage = new EditExamPage(editorKit, exam_individual));

		setCurrentPanel(EditExamPage.ID);
	}

}
