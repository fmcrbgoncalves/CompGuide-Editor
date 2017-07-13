package org.di.uminho.cguide.wizard.Create;

import org.apache.log4j.Logger;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage10;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage11;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage12;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage13alternative;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage13next;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage13parallel;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage13preferencealternative;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage2;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage3;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage4;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage5;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage6;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage7;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage8;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9_1;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9_2;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9_3;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9_4;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage9_5;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage1;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage2;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage3;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage4;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage5;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage6;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage7;
import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage8;
import org.di.uminho.cguide.wizard.Create.Condition.CreateConditionPage1;
import org.di.uminho.cguide.wizard.Create.Condition.CreateConditionPage2;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage2;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage3;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage4;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage5;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage6;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage7;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage8alternative;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage8next;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage8parallel;
import org.di.uminho.cguide.wizard.Create.Decision.CreateDecisionPage8preferencealternative;
import org.di.uminho.cguide.wizard.Create.End.CreateEndPage2;
import org.di.uminho.cguide.wizard.Create.End.CreateEndPage3;
import org.di.uminho.cguide.wizard.Create.Exam.CreateExamPage;
import org.di.uminho.cguide.wizard.Create.Formula.CreateFormulaPage2;
import org.di.uminho.cguide.wizard.Create.Formula.CreateFormulaPage3;
import org.di.uminho.cguide.wizard.Create.Formula.CreateFormulaPage4;
import org.di.uminho.cguide.wizard.Create.MedicationRecommendation.CreateMedicationRecommendationPage;
import org.di.uminho.cguide.wizard.Create.NonMedicationRecommendation.CreateNonMedicationRecommendationPage;
import org.di.uminho.cguide.wizard.Create.Option.CreateOptionPage2;
import org.di.uminho.cguide.wizard.Create.Option.CreateOptionPage3;
import org.di.uminho.cguide.wizard.Create.Parameter.CreateParameterPage;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage10;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage11;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage12;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage13alternative;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage13next;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage13parallel;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage13preferencealternative;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage2;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage3;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage4;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage5;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage6;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage7;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage8;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage9;
import org.di.uminho.cguide.wizard.Create.Procedure.CreateProcedurePage;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage2;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage3;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage4;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage5;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage6;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage7;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage8alternative;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage8next;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage8parallel;
import org.di.uminho.cguide.wizard.Create.Question.CreateQuestionPage8preferencealternative;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class CreateWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(CreateWizard.class);

	public SelectClassPage selectclasspage;

	public CreateCPGPage1 createcpgpage1;

	public CreateCPGPage2 createcpgpage2;

	public CreateCPGPage3 createcpgpage3;

	public CreateCPGPage4 createcpgpage4;

	public CreateCPGPage5 createcpgpage5;

	public CreateCPGPage6 createcpgpage6;

	public CreateCPGPage7 createcpgpage7;

	public CreateCPGPage8 createcpgpage8;

	public CreateConditionPage1 createconditionpage1;

	public CreateConditionPage2 createconditionpage2;

	public SelectClinicalTaskPage selectclinicaltaskpage;

	public CreatePlanPage2 createplanpage2;

	public CreatePlanPage3 createplanpage3;

	public CreatePlanPage4 createplanpage4;

	public CreatePlanPage5 createplanpage5;

	public CreatePlanPage6 createplanpage6;

	public CreatePlanPage7 createplanpage7;

	public CreatePlanPage8 createplanpage8;

	public CreatePlanPage9 createplanpage9;

	public CreatePlanPage10 createplanpage10;

	public CreatePlanPage11 createplanpage11;

	public CreatePlanPage12 createplanpage12;

	public CreatePlanPage13alternative createplanpage13alternative;

	public CreatePlanPage13next createplanpage13next;

	public CreatePlanPage13parallel createplanpage13parallel;

	public CreatePlanPage13preferencealternative createplanpage13preferencealternative;

	public CreateDecisionPage2 createdecisionpage2;

	public CreateDecisionPage3 createdecisionpage3;

	public CreateDecisionPage4 createdecisionpage4;

	public CreateDecisionPage5 createdecisionpage5;

	public CreateDecisionPage6 createdecisionpage6;

	public CreateDecisionPage7 createdecisionpage7;

	public CreateDecisionPage8alternative createdecisionpage8alternative;

	public CreateDecisionPage8next createdecisionpage8next;

	public CreateDecisionPage8parallel createdecisionpage8parallel;

	public CreateDecisionPage8preferencealternative createdecisionpage8preferencealternative;

	public CreateOptionPage2 createoptionpage2;

	public CreateOptionPage3 createoptionpage3;

	public CreateQuestionPage2 createquestionpage2;

	public CreateQuestionPage3 createquestionpage3;

	public CreateQuestionPage4 createquestionpage4;

	public CreateQuestionPage5 createquestionpage5;

	public CreateQuestionPage6 createquestionpage6;

	public CreateQuestionPage7 createquestionpage7;

	public CreateQuestionPage8alternative createquestionpage8alternative;

	public CreateQuestionPage8next createquestionpage8next;

	public CreateQuestionPage8parallel createquestionpage8parallel;

	public CreateQuestionPage8preferencealternative createquestionpage8preferencealternative;

	public CreateParameterPage createparameterpage;

	public CreateEndPage2 createendpage2;

	public CreateEndPage3 createendpage3;

	public CreateActionPage2 createactionpage2;

	public CreateActionPage3 createactionpage3;

	public CreateActionPage4 createactionpage4;

	public CreateActionPage5 createactionpage5;

	public CreateActionPage6 createactionpage6;

	public CreateActionPage7 createactionpage7;

	public CreateActionPage8 createactionpage8;

	public CreateActionPage9 createactionpage9;

	public CreateActionPage9_1 createactionpage9_1;

	public CreateActionPage9_2 createactionpage9_2;

	public CreateActionPage9_3 createactionpage9_3;

	public CreateActionPage9_4 createactionpage9_4;

	public CreateActionPage9_5 createactionpage9_5;

	public CreateActionPage10 createactionpage10;

	public CreateActionPage11 createactionpage11;

	public CreateActionPage12 createactionpage12;

	public CreateActionPage13alternative createactionpage13alternative;

	public CreateActionPage13next createactionpage13next;

	public CreateActionPage13parallel createactionpage13parallel;

	public CreateActionPage13preferencealternative createactionpage13preferencealternative;

	public CreateExamPage createexampage;

	public CreateProcedurePage createprocedurepage;

	public CreateNonMedicationRecommendationPage createnonmedicationrecommendationpage;

	public CreateMedicationRecommendationPage createmedicationrecommendationpage;

	public CreateFormulaPage2 createformulapage2;

	public CreateFormulaPage3 createformulapage3;

	public CreateFormulaPage4 createformulapage4;

	public SelectClinicalActionPage selectclinicalactionpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public CreateWizard(OWLEditorKit editorKit) {
		setTitle("Creation Wizard");
		this.owlModelManager = editorKit.getModelManager();
		registerWizardPanel(SelectClassPage.ID, selectclasspage = new SelectClassPage(editorKit));
		registerWizardPanel(CreateCPGPage1.ID, createcpgpage1 = new CreateCPGPage1(editorKit));
		registerWizardPanel(CreateCPGPage2.ID, createcpgpage2 = new CreateCPGPage2(editorKit));
		registerWizardPanel(CreateCPGPage3.ID, createcpgpage3 = new CreateCPGPage3(editorKit));
		registerWizardPanel(CreateCPGPage4.ID, createcpgpage4 = new CreateCPGPage4(editorKit));
		registerWizardPanel(CreateCPGPage5.ID, createcpgpage5 = new CreateCPGPage5(editorKit));
		registerWizardPanel(CreateCPGPage6.ID, createcpgpage6 = new CreateCPGPage6(editorKit));
		registerWizardPanel(CreateCPGPage7.ID, createcpgpage7 = new CreateCPGPage7(editorKit));
		registerWizardPanel(CreateCPGPage8.ID, createcpgpage8 = new CreateCPGPage8(editorKit));

		registerWizardPanel(CreateConditionPage1.ID, createconditionpage1 = new CreateConditionPage1(editorKit));
		registerWizardPanel(CreateConditionPage2.ID, createconditionpage2 = new CreateConditionPage2(editorKit));

		registerWizardPanel(SelectClinicalTaskPage.ID, selectclinicaltaskpage = new SelectClinicalTaskPage(editorKit));

		registerWizardPanel(CreatePlanPage2.ID, createplanpage2 = new CreatePlanPage2(editorKit));
		registerWizardPanel(CreatePlanPage3.ID, createplanpage3 = new CreatePlanPage3(editorKit));
		registerWizardPanel(CreatePlanPage4.ID, createplanpage4 = new CreatePlanPage4(editorKit));
		registerWizardPanel(CreatePlanPage5.ID, createplanpage5 = new CreatePlanPage5(editorKit));
		registerWizardPanel(CreatePlanPage6.ID, createplanpage6 = new CreatePlanPage6(editorKit));
		registerWizardPanel(CreatePlanPage7.ID, createplanpage7 = new CreatePlanPage7(editorKit));
		registerWizardPanel(CreatePlanPage8.ID, createplanpage8 = new CreatePlanPage8(editorKit));
		registerWizardPanel(CreatePlanPage9.ID, createplanpage9 = new CreatePlanPage9(editorKit));
		registerWizardPanel(CreatePlanPage10.ID, createplanpage10 = new CreatePlanPage10(editorKit));
		registerWizardPanel(CreatePlanPage11.ID, createplanpage11 = new CreatePlanPage11(editorKit));
		registerWizardPanel(CreatePlanPage12.ID, createplanpage12 = new CreatePlanPage12(editorKit));
		registerWizardPanel(CreatePlanPage13alternative.ID,
				createplanpage13alternative = new CreatePlanPage13alternative(editorKit));
		registerWizardPanel(CreatePlanPage13next.ID, createplanpage13next = new CreatePlanPage13next(editorKit));
		registerWizardPanel(CreatePlanPage13parallel.ID,
				createplanpage13parallel = new CreatePlanPage13parallel(editorKit));
		registerWizardPanel(CreatePlanPage13preferencealternative.ID,
				createplanpage13preferencealternative = new CreatePlanPage13preferencealternative(editorKit));

		registerWizardPanel(CreateDecisionPage2.ID, createdecisionpage2 = new CreateDecisionPage2(editorKit));
		registerWizardPanel(CreateDecisionPage3.ID, createdecisionpage3 = new CreateDecisionPage3(editorKit));
		registerWizardPanel(CreateDecisionPage4.ID, createdecisionpage4 = new CreateDecisionPage4(editorKit));
		registerWizardPanel(CreateDecisionPage5.ID, createdecisionpage5 = new CreateDecisionPage5(editorKit));
		registerWizardPanel(CreateDecisionPage6.ID, createdecisionpage6 = new CreateDecisionPage6(editorKit));
		registerWizardPanel(CreateDecisionPage7.ID, createdecisionpage7 = new CreateDecisionPage7(editorKit));
		registerWizardPanel(CreateDecisionPage8alternative.ID,
				createdecisionpage8alternative = new CreateDecisionPage8alternative(editorKit));
		registerWizardPanel(CreateDecisionPage8next.ID,
				createdecisionpage8next = new CreateDecisionPage8next(editorKit));
		registerWizardPanel(CreateDecisionPage8parallel.ID,
				createdecisionpage8parallel = new CreateDecisionPage8parallel(editorKit));
		registerWizardPanel(CreateDecisionPage8preferencealternative.ID,
				createdecisionpage8preferencealternative = new CreateDecisionPage8preferencealternative(editorKit));

		registerWizardPanel(CreateQuestionPage2.ID, createquestionpage2 = new CreateQuestionPage2(editorKit));
		registerWizardPanel(CreateQuestionPage3.ID, createquestionpage3 = new CreateQuestionPage3(editorKit));
		registerWizardPanel(CreateQuestionPage4.ID, createquestionpage4 = new CreateQuestionPage4(editorKit));
		registerWizardPanel(CreateQuestionPage5.ID, createquestionpage5 = new CreateQuestionPage5(editorKit));
		registerWizardPanel(CreateQuestionPage6.ID, createquestionpage6 = new CreateQuestionPage6(editorKit));
		registerWizardPanel(CreateQuestionPage7.ID, createquestionpage7 = new CreateQuestionPage7(editorKit));
		registerWizardPanel(CreateQuestionPage8alternative.ID,
				createquestionpage8alternative = new CreateQuestionPage8alternative(editorKit));
		registerWizardPanel(CreateQuestionPage8next.ID,
				createquestionpage8next = new CreateQuestionPage8next(editorKit));
		registerWizardPanel(CreateQuestionPage8parallel.ID,
				createquestionpage8parallel = new CreateQuestionPage8parallel(editorKit));
		registerWizardPanel(CreateQuestionPage8preferencealternative.ID,
				createquestionpage8preferencealternative = new CreateQuestionPage8preferencealternative(editorKit));

		registerWizardPanel(CreateEndPage2.ID, createendpage2 = new CreateEndPage2(editorKit));
		registerWizardPanel(CreateEndPage3.ID, createendpage3 = new CreateEndPage3(editorKit));

		registerWizardPanel(CreateActionPage2.ID, createactionpage2 = new CreateActionPage2(editorKit));
		registerWizardPanel(CreateActionPage3.ID, createactionpage3 = new CreateActionPage3(editorKit));
		registerWizardPanel(CreateActionPage4.ID, createactionpage4 = new CreateActionPage4(editorKit));
		registerWizardPanel(CreateActionPage5.ID, createactionpage5 = new CreateActionPage5(editorKit));
		registerWizardPanel(CreateActionPage6.ID, createactionpage6 = new CreateActionPage6(editorKit));
		registerWizardPanel(CreateActionPage7.ID, createactionpage7 = new CreateActionPage7(editorKit));
		registerWizardPanel(CreateActionPage8.ID, createactionpage8 = new CreateActionPage8(editorKit));
		registerWizardPanel(CreateActionPage9.ID, createactionpage9 = new CreateActionPage9(editorKit));
		registerWizardPanel(CreateActionPage9_1.ID, createactionpage9_1 = new CreateActionPage9_1(editorKit));
		registerWizardPanel(CreateActionPage9_2.ID, createactionpage9_2 = new CreateActionPage9_2(editorKit));
		registerWizardPanel(CreateActionPage9_3.ID, createactionpage9_3 = new CreateActionPage9_3(editorKit));
		registerWizardPanel(CreateActionPage9_4.ID, createactionpage9_4 = new CreateActionPage9_4(editorKit));
		registerWizardPanel(CreateActionPage9_5.ID, createactionpage9_5 = new CreateActionPage9_5(editorKit));
		registerWizardPanel(CreateActionPage10.ID, createactionpage10 = new CreateActionPage10(editorKit));
		registerWizardPanel(CreateActionPage11.ID, createactionpage11 = new CreateActionPage11(editorKit));
		registerWizardPanel(CreateActionPage12.ID, createactionpage12 = new CreateActionPage12(editorKit));
		registerWizardPanel(CreateActionPage13alternative.ID,
				createactionpage13alternative = new CreateActionPage13alternative(editorKit));
		registerWizardPanel(CreateActionPage13next.ID, createactionpage13next = new CreateActionPage13next(editorKit));
		registerWizardPanel(CreateActionPage13parallel.ID,
				createactionpage13parallel = new CreateActionPage13parallel(editorKit));
		registerWizardPanel(CreateActionPage13preferencealternative.ID,
				createactionpage13preferencealternative = new CreateActionPage13preferencealternative(editorKit));

		registerWizardPanel(SelectClinicalActionPage.ID,
				selectclinicalactionpage = new SelectClinicalActionPage(editorKit));

		registerWizardPanel(CreateExamPage.ID, createexampage = new CreateExamPage(editorKit));

		registerWizardPanel(CreateProcedurePage.ID, createprocedurepage = new CreateProcedurePage(editorKit));
		registerWizardPanel(CreateNonMedicationRecommendationPage.ID,

				createnonmedicationrecommendationpage = new CreateNonMedicationRecommendationPage(editorKit));
		registerWizardPanel(CreateMedicationRecommendationPage.ID,

				createmedicationrecommendationpage = new CreateMedicationRecommendationPage(editorKit));

		registerWizardPanel(CreateFormulaPage2.ID, createformulapage2 = new CreateFormulaPage2(editorKit));
		registerWizardPanel(CreateFormulaPage3.ID, createformulapage3 = new CreateFormulaPage3(editorKit));
		registerWizardPanel(CreateFormulaPage4.ID, createformulapage4 = new CreateFormulaPage4(editorKit));

		registerWizardPanel(CreateOptionPage2.ID, createoptionpage2 = new CreateOptionPage2(editorKit));
		registerWizardPanel(CreateOptionPage3.ID, createoptionpage3 = new CreateOptionPage3(editorKit));

		registerWizardPanel(CreateParameterPage.ID, createparameterpage = new CreateParameterPage(editorKit));

		setCurrentPanel(SelectClassPage.ID);

	}
}
