package org.di.uminho.cguide.wizard.Edit.Question;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditQuestionWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditQuestionWizard.class);

	public EditQuestionPage2 editquestionpage2;

	public EditQuestionPage3 editquestionpage3;

	public EditQuestionPage4 editquestionpage4;

	public EditQuestionPage5 editquestionpage5;

	public EditQuestionPage6 editquestionpage6;

	public EditQuestionPage7 editquestionpage7;

	public EditQuestionPage8alternative editquestionpage8alternative;

	public EditQuestionPage8next editquestionpage8next;

	public EditQuestionPage8parallel editquestionpage8parallel;

	public EditQuestionPage8preferencealternative editquestionpage8preferencealternative;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditQuestionWizard(OWLEditorKit editorKit, String question_individual) {
		setTitle("Question Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditQuestionPage2.ID,
				editquestionpage2 = new EditQuestionPage2(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage3.ID,
				editquestionpage3 = new EditQuestionPage3(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage4.ID,
				editquestionpage4 = new EditQuestionPage4(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage5.ID,
				editquestionpage5 = new EditQuestionPage5(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage6.ID,
				editquestionpage6 = new EditQuestionPage6(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage7.ID,
				editquestionpage7 = new EditQuestionPage7(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage8alternative.ID,
				editquestionpage8alternative = new EditQuestionPage8alternative(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage8next.ID,
				editquestionpage8next = new EditQuestionPage8next(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage8parallel.ID,
				editquestionpage8parallel = new EditQuestionPage8parallel(editorKit, question_individual));
		registerWizardPanel(EditQuestionPage8preferencealternative.ID,
				editquestionpage8preferencealternative = new EditQuestionPage8preferencealternative(editorKit,
						question_individual));

		setCurrentPanel(EditQuestionPage2.ID);
	}

}
