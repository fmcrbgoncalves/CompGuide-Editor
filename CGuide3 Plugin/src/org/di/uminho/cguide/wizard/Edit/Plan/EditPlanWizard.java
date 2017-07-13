package org.di.uminho.cguide.wizard.Edit.Plan;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditPlanWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditPlanWizard.class);

	public EditPlanPage2 editplanpage2;

	public EditPlanPage3 editplanpage3;

	public EditPlanPage4 editplanpage4;

	public EditPlanPage5 editplanpage5;

	public EditPlanPage6 editplanpage6;

	public EditPlanPage7 editplanpage7;

	public EditPlanPage8 editplanpage8;

	public EditPlanPage9 editplanpage9;

	public EditPlanPage10 editplanpage10;

	public EditPlanPage11 editplanpage11;

	public EditPlanPage12 editplanpage12;

	public EditPlanPage13alternative editplanpage13alternative;

	public EditPlanPage13next editplanpage13next;

	public EditPlanPage13parallel editplanpage13parallel;

	public EditPlanPage13preferencealternative editplanpage13preferencealternative;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditPlanWizard(OWLEditorKit editorKit, String plan_individual) {
		setTitle("Plan Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditPlanPage2.ID, editplanpage2 = new EditPlanPage2(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage3.ID, editplanpage3 = new EditPlanPage3(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage4.ID, editplanpage4 = new EditPlanPage4(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage5.ID, editplanpage5 = new EditPlanPage5(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage6.ID, editplanpage6 = new EditPlanPage6(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage7.ID, editplanpage7 = new EditPlanPage7(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage8.ID, editplanpage8 = new EditPlanPage8(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage9.ID, editplanpage9 = new EditPlanPage9(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage10.ID, editplanpage10 = new EditPlanPage10(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage11.ID, editplanpage11 = new EditPlanPage11(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage12.ID, editplanpage12 = new EditPlanPage12(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage13alternative.ID,
				editplanpage13alternative = new EditPlanPage13alternative(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage13next.ID,
				editplanpage13next = new EditPlanPage13next(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage13parallel.ID,
				editplanpage13parallel = new EditPlanPage13parallel(editorKit, plan_individual));
		registerWizardPanel(EditPlanPage13preferencealternative.ID,
				editplanpage13preferencealternative = new EditPlanPage13preferencealternative(editorKit,
						plan_individual));

		setCurrentPanel(EditPlanPage2.ID);
	}

}
