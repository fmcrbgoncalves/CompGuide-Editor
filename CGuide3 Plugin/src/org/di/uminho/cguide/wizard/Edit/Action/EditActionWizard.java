package org.di.uminho.cguide.wizard.Edit.Action;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditActionWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditActionWizard.class);

	public EditActionPage2 editactionpage2;

	public EditActionPage3 editactionpage3;

	public EditActionPage4 editactionpage4;

	public EditActionPage5 editactionpage5;

	public EditActionPage6 editactionpage6;

	public EditActionPage7 editactionpage7;

	public EditActionPage8 editactionpage8;

	public EditActionPage9 editactionpage9;

	public EditActionPage9_1 editactionpage9_1;

	public EditActionPage9_2 editactionpage9_2;

	public EditActionPage9_3 editactionpage9_3;

	public EditActionPage9_4 editactionpage9_4;

	public EditActionPage9_5 editactionpage9_5;

	public EditActionPage10 editactionpage10;

	public EditActionPage11 editactionpage11;

	public EditActionPage12 editactionpage12;

	public EditActionPage13alternative editactionpage13alternative;

	public EditActionPage13next editactionpage13next;

	public EditActionPage13parallel editactionpage13parallel;

	public EditActionPage13preferencealternative editactionpage13preferencealternative;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditActionWizard(OWLEditorKit editorKit, String action_individual) {
		setTitle("Action Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditActionPage2.ID, editactionpage2 = new EditActionPage2(editorKit, action_individual));
		registerWizardPanel(EditActionPage3.ID, editactionpage3 = new EditActionPage3(editorKit, action_individual));
		registerWizardPanel(EditActionPage4.ID, editactionpage4 = new EditActionPage4(editorKit, action_individual));
		registerWizardPanel(EditActionPage5.ID, editactionpage5 = new EditActionPage5(editorKit, action_individual));
		registerWizardPanel(EditActionPage6.ID, editactionpage6 = new EditActionPage6(editorKit, action_individual));
		registerWizardPanel(EditActionPage7.ID, editactionpage7 = new EditActionPage7(editorKit, action_individual));
		registerWizardPanel(EditActionPage8.ID, editactionpage8 = new EditActionPage8(editorKit, action_individual));
		registerWizardPanel(EditActionPage9.ID, editactionpage9 = new EditActionPage9(editorKit, action_individual));
		registerWizardPanel(EditActionPage9_1.ID,
				editactionpage9_1 = new EditActionPage9_1(editorKit, action_individual));
		registerWizardPanel(EditActionPage9_2.ID,
				editactionpage9_2 = new EditActionPage9_2(editorKit, action_individual));
		registerWizardPanel(EditActionPage9_3.ID,
				editactionpage9_3 = new EditActionPage9_3(editorKit, action_individual));
		registerWizardPanel(EditActionPage9_4.ID,
				editactionpage9_4 = new EditActionPage9_4(editorKit, action_individual));
		registerWizardPanel(EditActionPage9_5.ID,
				editactionpage9_5 = new EditActionPage9_5(editorKit, action_individual));
		registerWizardPanel(EditActionPage10.ID, editactionpage10 = new EditActionPage10(editorKit, action_individual));
		registerWizardPanel(EditActionPage11.ID, editactionpage11 = new EditActionPage11(editorKit, action_individual));
		registerWizardPanel(EditActionPage12.ID, editactionpage12 = new EditActionPage12(editorKit, action_individual));
		registerWizardPanel(EditActionPage13alternative.ID,
				editactionpage13alternative = new EditActionPage13alternative(editorKit, action_individual));
		registerWizardPanel(EditActionPage13next.ID,
				editactionpage13next = new EditActionPage13next(editorKit, action_individual));
		registerWizardPanel(EditActionPage13parallel.ID,
				editactionpage13parallel = new EditActionPage13parallel(editorKit, action_individual));
		registerWizardPanel(EditActionPage13preferencealternative.ID,
				editactionpage13preferencealternative = new EditActionPage13preferencealternative(editorKit,
						action_individual));

		setCurrentPanel(EditActionPage2.ID);
	}

}
