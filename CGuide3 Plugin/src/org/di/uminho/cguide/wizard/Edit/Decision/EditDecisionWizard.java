package org.di.uminho.cguide.wizard.Edit.Decision;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditDecisionWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditDecisionWizard.class);

	public EditDecisionPage2 editdecisionpage2;

	public EditDecisionPage3 editdecisionpage3;

	public EditDecisionPage4 editdecisionpage4;

	public EditDecisionPage5 editdecisionpage5;

	public EditDecisionPage6 editdecisionpage6;

	public EditDecisionPage7 editdecisionpage7;

	public EditDecisionPage8alternative editdecisionpage8alternative;

	public EditDecisionPage8next editdecisionpage8next;

	public EditDecisionPage8parallel editdecisionpage8parallel;

	public EditDecisionPage8preferencealternative editdecisionpage8preferencealternative;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditDecisionWizard(OWLEditorKit editorKit, String decision_individual) {
		setTitle("Decision Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditDecisionPage2.ID,
				editdecisionpage2 = new EditDecisionPage2(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage3.ID,
				editdecisionpage3 = new EditDecisionPage3(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage4.ID,
				editdecisionpage4 = new EditDecisionPage4(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage5.ID,
				editdecisionpage5 = new EditDecisionPage5(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage6.ID,
				editdecisionpage6 = new EditDecisionPage6(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage7.ID,
				editdecisionpage7 = new EditDecisionPage7(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage8alternative.ID,
				editdecisionpage8alternative = new EditDecisionPage8alternative(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage8next.ID,
				editdecisionpage8next = new EditDecisionPage8next(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage8parallel.ID,
				editdecisionpage8parallel = new EditDecisionPage8parallel(editorKit, decision_individual));
		registerWizardPanel(EditDecisionPage8preferencealternative.ID,
				editdecisionpage8preferencealternative = new EditDecisionPage8preferencealternative(editorKit,
						decision_individual));

		setCurrentPanel(EditDecisionPage2.ID);
	}

}
