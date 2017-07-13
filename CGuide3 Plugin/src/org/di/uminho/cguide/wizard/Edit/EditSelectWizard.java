package org.di.uminho.cguide.wizard.Edit;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditSelectWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditSelectWizard.class);

	public SelectClassPage selectclasspage;

	public SelectClinicalTaskPage selectclinicaltaskpage;

	public SelectClinicalActionPage selectclinicalactionpage;

	public EditCPGPage editcpgpage;

	public EditPlanPage editplanpage;

	public EditActionPage editactionpage;

	public EditDecisionPage editdecisionpage;

	public EditQuestionPage editquestionpage;

	public EditEndPage editendpage;

	public EditExamPage editexampage;

	public EditFormulaPage editformulapage;

	public EditProcedurePage editprocedurepage;

	public EditMedicalRecommendationPage editmedicalrecommendationpage;

	public EditNonMedicalRecommendationPage editnonmedicalrecommendationpage;

	public EditConditionPage editconditionpage;

	public EditOptionPage editoptionpage;

	public EditParameterPage editparameterpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditSelectWizard(OWLEditorKit editorKit) {
		setTitle("Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();
		registerWizardPanel(SelectClassPage.ID, selectclasspage = new SelectClassPage(editorKit));
		registerWizardPanel(SelectClinicalTaskPage.ID, selectclinicaltaskpage = new SelectClinicalTaskPage(editorKit));
		registerWizardPanel(SelectClinicalActionPage.ID,
				selectclinicalactionpage = new SelectClinicalActionPage(editorKit));

		registerWizardPanel(EditCPGPage.ID, editcpgpage = new EditCPGPage(editorKit));

		registerWizardPanel(EditPlanPage.ID, editplanpage = new EditPlanPage(editorKit));
		registerWizardPanel(EditActionPage.ID, editactionpage = new EditActionPage(editorKit));
		registerWizardPanel(EditDecisionPage.ID, editdecisionpage = new EditDecisionPage(editorKit));
		registerWizardPanel(EditQuestionPage.ID, editquestionpage = new EditQuestionPage(editorKit));
		registerWizardPanel(EditEndPage.ID, editendpage = new EditEndPage(editorKit));

		registerWizardPanel(EditExamPage.ID, editexampage = new EditExamPage(editorKit));
		registerWizardPanel(EditFormulaPage.ID, editformulapage = new EditFormulaPage(editorKit));
		registerWizardPanel(EditProcedurePage.ID, editprocedurepage = new EditProcedurePage(editorKit));
		registerWizardPanel(EditMedicalRecommendationPage.ID,
				editmedicalrecommendationpage = new EditMedicalRecommendationPage(editorKit));
		registerWizardPanel(EditNonMedicalRecommendationPage.ID,
				editnonmedicalrecommendationpage = new EditNonMedicalRecommendationPage(editorKit));

		registerWizardPanel(EditConditionPage.ID, editconditionpage = new EditConditionPage(editorKit));
		registerWizardPanel(EditOptionPage.ID, editoptionpage = new EditOptionPage(editorKit));
		registerWizardPanel(EditParameterPage.ID, editparameterpage = new EditParameterPage(editorKit));

		setCurrentPanel(SelectClassPage.ID);

	}
}
