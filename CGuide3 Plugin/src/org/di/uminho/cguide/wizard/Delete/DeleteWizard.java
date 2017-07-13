package org.di.uminho.cguide.wizard.Delete;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class DeleteWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(DeleteWizard.class);

	public SelectClassPage selectclasspage;

	public SelectClinicalTaskPage selectclinicaltaskpage;

	public SelectClinicalActionPage selectclinicalactionpage;

	public DeleteCPGPage deletecpgpage;

	public DeletePlanPage deleteplanpage;

	public DeleteActionPage deleteactionpage;

	public DeleteDecisionPage deletedecisionpage;

	public DeleteQuestionPage deletequestionpage;

	public DeleteEndPage deleteendpage;

	public DeleteExamPage deleteexampage;

	public DeleteFormulaPage deleteformulapage;

	public DeleteProcedurePage deleteprocedurepage;

	public DeleteMedicalRecommendationPage deletemedicalrecommendationpage;

	public DeleteNonMedicalRecommendationPage deletenonmedicalrecommendationpage;

	public DeleteConditionPage deleteconditionpage;

	public DeleteOptionPage deleteoptionpage;

	public DeleteParameterPage deleteparameterpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public DeleteWizard(OWLEditorKit editorKit) {
		setTitle("Deletion Wizard");
		this.owlModelManager = editorKit.getModelManager();
		registerWizardPanel(SelectClassPage.ID, selectclasspage = new SelectClassPage(editorKit));
		registerWizardPanel(SelectClinicalTaskPage.ID, selectclinicaltaskpage = new SelectClinicalTaskPage(editorKit));
		registerWizardPanel(SelectClinicalActionPage.ID,
				selectclinicalactionpage = new SelectClinicalActionPage(editorKit));

		registerWizardPanel(DeleteCPGPage.ID, deletecpgpage = new DeleteCPGPage(editorKit));

		registerWizardPanel(DeletePlanPage.ID, deleteplanpage = new DeletePlanPage(editorKit));
		registerWizardPanel(DeleteActionPage.ID, deleteactionpage = new DeleteActionPage(editorKit));
		registerWizardPanel(DeleteDecisionPage.ID, deletedecisionpage = new DeleteDecisionPage(editorKit));
		registerWizardPanel(DeleteQuestionPage.ID, deletequestionpage = new DeleteQuestionPage(editorKit));
		registerWizardPanel(DeleteEndPage.ID, deleteendpage = new DeleteEndPage(editorKit));

		registerWizardPanel(DeleteExamPage.ID, deleteexampage = new DeleteExamPage(editorKit));
		registerWizardPanel(DeleteFormulaPage.ID, deleteformulapage = new DeleteFormulaPage(editorKit));
		registerWizardPanel(DeleteProcedurePage.ID, deleteprocedurepage = new DeleteProcedurePage(editorKit));
		registerWizardPanel(DeleteMedicalRecommendationPage.ID,
				deletemedicalrecommendationpage = new DeleteMedicalRecommendationPage(editorKit));
		registerWizardPanel(DeleteNonMedicalRecommendationPage.ID,
				deletenonmedicalrecommendationpage = new DeleteNonMedicalRecommendationPage(editorKit));

		registerWizardPanel(DeleteConditionPage.ID, deleteconditionpage = new DeleteConditionPage(editorKit));
		registerWizardPanel(DeleteOptionPage.ID, deleteoptionpage = new DeleteOptionPage(editorKit));
		registerWizardPanel(DeleteParameterPage.ID, deleteparameterpage = new DeleteParameterPage(editorKit));

		setCurrentPanel(SelectClassPage.ID);

	}
}
