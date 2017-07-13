package org.di.uminho.cguide.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

import org.di.uminho.cguide.functions.AppZip;
import org.di.uminho.cguide.functions.ContentDownloader;
import org.di.uminho.cguide.functions.TCPClient;
import org.di.uminho.cguide.wizard.Create.CreateWizard;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage10;
import org.di.uminho.cguide.wizard.Create.Action.CreateActionPage3;
import org.di.uminho.cguide.wizard.Create.Option.CreateOptionPage2;
import org.di.uminho.cguide.wizard.Create.Parameter.CreateParameterPage;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage10;
import org.di.uminho.cguide.wizard.Create.Plan.CreatePlanPage3;
import org.di.uminho.cguide.wizard.Delete.DeleteWizard;
import org.di.uminho.cguide.wizard.Edit.EditSelectWizard;
import org.di.uminho.cguide.wizard.Edit.Action.EditActionPage10;
import org.di.uminho.cguide.wizard.Edit.Action.EditActionPage3;
import org.di.uminho.cguide.wizard.Edit.Action.EditActionWizard;
import org.di.uminho.cguide.wizard.Edit.CPG.EditCPGWizard;
import org.di.uminho.cguide.wizard.Edit.Condition.EditConditionWizard;
import org.di.uminho.cguide.wizard.Edit.Decision.EditDecisionWizard;
import org.di.uminho.cguide.wizard.Edit.End.EditEndWizard;
import org.di.uminho.cguide.wizard.Edit.Exam.EditExamWizard;
import org.di.uminho.cguide.wizard.Edit.Formula.EditFormulaWizard;
import org.di.uminho.cguide.wizard.Edit.MedicationRecommendation.EditMedicationRecommendationWizard;
import org.di.uminho.cguide.wizard.Edit.NonMedicationRecommendation.EditNonMedicationRecommendationWizard;
import org.di.uminho.cguide.wizard.Edit.Option.EditOptionPage2;
import org.di.uminho.cguide.wizard.Edit.Option.EditOptionWizard;
import org.di.uminho.cguide.wizard.Edit.Parameter.EditParameterPage2;
import org.di.uminho.cguide.wizard.Edit.Parameter.EditParameterWizard;
import org.di.uminho.cguide.wizard.Edit.Plan.EditPlanPage10;
import org.di.uminho.cguide.wizard.Edit.Plan.EditPlanPage3;
import org.di.uminho.cguide.wizard.Edit.Plan.EditPlanWizard;
import org.di.uminho.cguide.wizard.Edit.Procedure.EditProcedureWizard;
import org.di.uminho.cguide.wizard.Edit.Question.EditQuestionWizard;
import org.di.uminho.cguide.wizard.Upload.ShareWizard;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.OWLWorkspace;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerChangeEvent;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Metrics extends JPanel {
	private static final long serialVersionUID = -2017045836890114258L;

	private JLabel textComponent = new JLabel();

	private JButton createButton = new JButton("Create");
	private ActionListener createAction = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			CreateWizard a = new CreateWizard(editorkit);
			int code = a.showModalDialog();
			if (code == Wizard.FINISH_RETURN_CODE) {
				// Opcao CPG
				if (a.selectclasspage.choice == 1) {
					CreateCPG(a.createcpgpage1.guidelineName_jtext.getText(),
							a.createcpgpage1.autorship_jtext.getText(),
							a.createcpgpage1.guidelineDescription_jtext.getText(), a.createcpgpage2.model,
							a.createcpgpage3.model, a.createcpgpage4.model, a.createcpgpage5.model_added,
							a.createcpgpage6.model_added, a.createcpgpage7.model_added,
							(String) a.createcpgpage8.list_total.getSelectedValue());
				}
				// Opcao ClinicalTask
				else if (a.selectclasspage.choice == 2) { // Plan Selected
					if (a.selectclinicaltaskpage.choice == 1) {
						CreatePlan(a.createplanpage2.plan_description_jtext.getText(), a.createplanpage3,
								a.createplanpage4.model_added, (String) a.createplanpage5.list_total.getSelectedValue(),
								a.createplanpage6.model_added, a.createplanpage7.model_added,
								a.createplanpage8.model_added, a.createplanpage9.model_added, a.createplanpage10,
								(String) a.createplanpage11.list_total.getSelectedValue(), a.createplanpage12.choice,
								a.createplanpage13alternative.model_added,
								(String) a.createplanpage13next.list_total.getSelectedValue(),
								a.createplanpage13parallel.model_added,
								a.createplanpage13preferencealternative.model_added);
					} else if (a.selectclinicaltaskpage.choice == 2) { // Action
						CreateAction(a.createactionpage2.plan_description_jtext.getText(), a.createactionpage3,
								a.createactionpage4.model_added,
								(String) a.createactionpage5.list_total.getSelectedValue(),
								a.createactionpage6.model_added, a.createactionpage7.model_added,
								a.createactionpage8.model_added, a.createactionpage9.choice,
								a.createactionpage9_1.model_added, a.createactionpage9_2.model_added,
								a.createactionpage9_3.model_added, a.createactionpage9_4.model_added,
								a.createactionpage9_5.model_added, a.createactionpage10,
								(String) a.createactionpage11.list_total.getSelectedValue(),
								a.createactionpage12.choice, a.createactionpage13alternative.model_added,
								(String) a.createactionpage13next.list_total.getSelectedValue(),
								a.createactionpage13parallel.model_added,
								a.createactionpage13preferencealternative.model_added);

					} else if (a.selectclinicaltaskpage.choice == 3) { // Decision
						CreateDecision(a.createdecisionpage2.plan_description_jtext.getText(),
								a.createdecisionpage3.model_added, a.createdecisionpage4.model_added,
								a.createdecisionpage5.model_added,
								(String) a.createdecisionpage6.list_total.getSelectedValue(),
								a.createdecisionpage7.choice, a.createdecisionpage8alternative.model_added,
								(String) a.createdecisionpage8next.list_total.getSelectedValue(),
								a.createdecisionpage8parallel.model_added,
								a.createdecisionpage8preferencealternative.model_added);
					} else if (a.selectclinicaltaskpage.choice == 4) { // Question
						CreateQuestion(a.createquestionpage2.plan_description_jtext.getText(),
								a.createquestionpage3.model_added, a.createquestionpage4.model_added,
								a.createquestionpage5.model_added,
								(String) a.createquestionpage6.list_total.getSelectedValue(),
								a.createquestionpage7.choice, a.createquestionpage8alternative.model_added,
								(String) a.createquestionpage8next.list_total.getSelectedValue(),
								a.createquestionpage8parallel.model_added,
								a.createquestionpage8preferencealternative.model_added);

					} else if (a.selectclinicaltaskpage.choice == 5) { // End
						CreateEnd(a.createendpage2.plan_description_jtext.getText(), a.createendpage3.model_added);

					}
				}
				// Opcao Condition
				else if (a.selectclasspage.choice == 3) {
					if (a.createconditionpage2.temporalrestriction_type == 0) {
						if (a.createconditionpage1.condition_type == 0) {
							CreateCondition(a.createconditionpage1.condition_type,
									a.createconditionpage1.numericalvalue_textfield.getText(),
									(String) a.createconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
									a.createconditionpage1.parameteridentifier_textfield.getText(),
									a.createconditionpage1.conditionparameter_textfield.getText(),
									a.createconditionpage1.unit_textfield.getText(),
									a.createconditionpage2.maxtemporalrestriction_textfield.getText(),
									a.createconditionpage2.mintemporalrestriction_textfield.getText(),
									(String) a.createconditionpage2.temporaloperator_combobox.getSelectedItem(),
									(String) a.createconditionpage2.temporalunit_combobox.getSelectedItem());
						} else if (a.createconditionpage1.condition_type == 1) {
							CreateCondition(a.createconditionpage1.condition_type,
									a.createconditionpage1.qualitativevalue_textfield.getText(),
									(String) a.createconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
									a.createconditionpage1.parameteridentifier_textfield.getText(),
									a.createconditionpage1.conditionparameter_textfield.getText(),
									a.createconditionpage1.unit_textfield.getText(),
									a.createconditionpage2.maxtemporalrestriction_textfield.getText(),
									a.createconditionpage2.mintemporalrestriction_textfield.getText(),
									(String) a.createconditionpage2.temporaloperator_combobox.getSelectedItem(),
									(String) a.createconditionpage2.temporalunit_combobox.getSelectedItem());
						}
					} else if (a.createconditionpage2.temporalrestriction_type == 1) {
						if (a.createconditionpage1.condition_type == 0) {
							CreateCondition(a.createconditionpage1.condition_type,
									a.createconditionpage1.numericalvalue_textfield.getText(),
									(String) a.createconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
									a.createconditionpage1.parameteridentifier_textfield.getText(),
									a.createconditionpage1.conditionparameter_textfield.getText(),
									a.createconditionpage1.unit_textfield.getText(),
									a.createconditionpage2.temporalrestriction_textfield.getText(),
									(String) a.createconditionpage2.temporaloperator_combobox.getSelectedItem(),
									(String) a.createconditionpage2.temporalunit_combobox.getSelectedItem());
						} else if (a.createconditionpage1.condition_type == 1) {
							CreateCondition(a.createconditionpage1.condition_type,
									a.createconditionpage1.qualitativevalue_textfield.getText(),
									(String) a.createconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
									a.createconditionpage1.parameteridentifier_textfield.getText(),
									a.createconditionpage1.conditionparameter_textfield.getText(),
									a.createconditionpage1.unit_textfield.getText(),
									a.createconditionpage2.temporalrestriction_textfield.getText(),
									(String) a.createconditionpage2.temporaloperator_combobox.getSelectedItem(),
									(String) a.createconditionpage2.temporalunit_combobox.getSelectedItem());
						}
					}
				}
				// Opcao ClinicalActionType
				else if (a.selectclasspage.choice == 4) {
					if (a.selectclinicalactionpage.choice == 1) { // Exam
						CreateExam(a.createexampage.exam_name_jtext.getText(),
								a.createexampage.exam_description_jtext.getText());
					} else if (a.selectclinicalactionpage.choice == 2) { // Formula
						CreateFormula(a.createformulapage2.formula_description_jtext.getText(),
								(String) a.createformulapage3.list_total.getSelectedValue(),
								a.createformulapage4.model_added);
					} else if (a.selectclinicalactionpage.choice == 3) { // Procedure
						CreateProcedure(a.createprocedurepage.procedure_name_jtext.getText(),
								a.createprocedurepage.procedure_description_jtext.getText());
					} else if (a.selectclinicalactionpage.choice == 4) { // Medical
																			// Recommendation
						CreateMedicationRecommendation(
								a.createmedicationrecommendationpage.medicationrecommendation_name_jtext.getText(),
								a.createmedicationrecommendationpage.medicationrecommendation_description_jtext
										.getText(),
								a.createmedicationrecommendationpage.medicationrecommendation_activeIngredient_jtext
										.getText(),
								a.createmedicationrecommendationpage.medicationrecommendation_dosage_jtext.getText(),
								a.createmedicationrecommendationpage.medicationrecommendation_pharmaceuticalForm_jtext
										.getText(),
								a.createmedicationrecommendationpage.medicationrecommendation_posology_jtext.getText());
					} else if (a.selectclinicalactionpage.choice == 5) { // Non-Medical
																			// Recommendation
						CreateNonMedicationRecommendation(
								a.createnonmedicationrecommendationpage.nonmedicationrecommendation_name_jtext
										.getText(),
								a.createnonmedicationrecommendationpage.nonmedicationrecommendation_description_jtext
										.getText());
					}
				}
				// Opcao Option
				else if (a.selectclasspage.choice == 5) {
					CreateOption(a.createoptionpage2, a.createoptionpage3.model_added);
				}
				// Opcao Parameter
				else if (a.selectclasspage.choice == 6) {
					CreateParameter(a.createparameterpage);
				}
			}
			recalculate();
			manager.fireEvent(EventType.ACTIVE_ONTOLOGY_CHANGED);
		}
	};
	private JButton editButton = new JButton("Edit");
	private ActionListener editAction = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			EditSelectWizard a = new EditSelectWizard(editorkit);
			int code = a.showModalDialog();
			int code_aux;
			if (code == Wizard.FINISH_RETURN_CODE) {
				// Opcao CPG
				if (a.selectclasspage.choice == 1) {
					EditCPGWizard b = new EditCPGWizard(editorkit,
							(String) a.editcpgpage.list_total.getSelectedValue());
					code_aux = b.showModalDialog();
					if (code_aux == Wizard.FINISH_RETURN_CODE) {
						EditCPG((String) a.editcpgpage.list_total.getSelectedValue(),
								b.editcpgpage1.guidelineName_jtext.getText(), b.editcpgpage1.autorship_jtext.getText(),
								b.editcpgpage1.guidelineDescription_jtext.getText(), b.editcpgpage2.model,
								b.editcpgpage3.model, b.editcpgpage4.model, b.editcpgpage5.model_added,
								b.editcpgpage6.model_added, b.editcpgpage7.model_added,
								(String) b.editcpgpage8.list_total.getSelectedValue());
					}
				}
				// Opcao ClinicalTask
				else if (a.selectclasspage.choice == 2) { // Plan Selected
					if (a.selectclinicaltaskpage.choice == 1) {
						EditPlanWizard b = new EditPlanWizard(editorkit,
								(String) a.editplanpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditPlan((String) a.editplanpage.list_total.getSelectedValue(),
									b.editplanpage2.plan_description_jtext.getText(), b.editplanpage3,
									b.editplanpage4.model_added, (String) b.editplanpage5.list_total.getSelectedValue(),
									b.editplanpage6.model_added, b.editplanpage7.model_added,
									b.editplanpage8.model_added, b.editplanpage9.model_added, b.editplanpage10,
									(String) b.editplanpage11.list_total.getSelectedValue(), b.editplanpage12.choice,
									b.editplanpage13alternative.model_added,
									(String) b.editplanpage13next.list_total.getSelectedValue(),
									b.editplanpage13parallel.model_added,
									b.editplanpage13preferencealternative.model_added);
						}
					} else if (a.selectclinicaltaskpage.choice == 2) { // Action
						EditActionWizard b = new EditActionWizard(editorkit,
								(String) a.editactionpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditAction((String) a.editactionpage.list_total.getSelectedValue(),
									b.editactionpage2.plan_description_jtext.getText(), b.editactionpage3,
									b.editactionpage4.model_added,
									(String) b.editactionpage5.list_total.getSelectedValue(),
									b.editactionpage6.model_added, b.editactionpage7.model_added,
									b.editactionpage8.model_added, b.editactionpage9.choice,
									b.editactionpage9_1.model_added, b.editactionpage9_2.model_added,
									b.editactionpage9_3.model_added, b.editactionpage9_4.model_added,
									b.editactionpage9_5.model_added, b.editactionpage10,
									(String) b.editactionpage11.list_total.getSelectedValue(),
									b.editactionpage12.choice, b.editactionpage13alternative.model_added,
									(String) b.editactionpage13next.list_total.getSelectedValue(),
									b.editactionpage13parallel.model_added,
									b.editactionpage13preferencealternative.model_added);
						}
					} else if (a.selectclinicaltaskpage.choice == 3) { // Decision
						EditDecisionWizard b = new EditDecisionWizard(editorkit,
								(String) a.editdecisionpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditDecision((String) a.editdecisionpage.list_total.getSelectedValue(),
									b.editdecisionpage2.plan_description_jtext.getText(),
									b.editdecisionpage3.model_added, b.editdecisionpage4.model_added,
									b.editdecisionpage5.model_added,
									(String) b.editdecisionpage6.list_total.getSelectedValue(),
									b.editdecisionpage7.choice, b.editdecisionpage8alternative.model_added,
									(String) b.editdecisionpage8next.list_total.getSelectedValue(),
									b.editdecisionpage8parallel.model_added,
									b.editdecisionpage8preferencealternative.model_added);
						}
					} else if (a.selectclinicaltaskpage.choice == 4) { // Question
						EditQuestionWizard b = new EditQuestionWizard(editorkit,
								(String) a.editquestionpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditQuestion((String) a.editquestionpage.list_total.getSelectedValue(),
									b.editquestionpage2.plan_description_jtext.getText(),
									b.editquestionpage3.model_added, b.editquestionpage4.model_added,
									b.editquestionpage5.model_added,
									(String) b.editquestionpage6.list_total.getSelectedValue(),
									b.editquestionpage7.choice, b.editquestionpage8alternative.model_added,
									(String) b.editquestionpage8next.list_total.getSelectedValue(),
									b.editquestionpage8parallel.model_added,
									b.editquestionpage8preferencealternative.model_added);
						}
					} else if (a.selectclinicaltaskpage.choice == 5) { // End
						EditEndWizard b = new EditEndWizard(editorkit,
								(String) a.editendpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditEnd((String) a.editendpage.list_total.getSelectedValue(),
									b.editendpage2.plan_description_jtext.getText(), b.editendpage3.model_added);
						}
					}
				}
				// Opcao Condition
				else if (a.selectclasspage.choice == 3) {
					EditConditionWizard b = new EditConditionWizard(editorkit,
							(String) a.editconditionpage.list_total.getSelectedValue());
					code_aux = b.showModalDialog();
					if (code_aux == Wizard.FINISH_RETURN_CODE) {
						if (b.editconditionpage2.temporalrestriction_type == 0) {
							if (b.editconditionpage1.condition_type == 0) {
								EditCondition((String) a.editconditionpage.list_total.getSelectedValue(),
										b.editconditionpage1.condition_type,
										b.editconditionpage1.numericalvalue_textfield.getText(),
										(String) b.editconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
										b.editconditionpage1.parameteridentifier_textfield.getText(),
										b.editconditionpage1.conditionparameter_textfield.getText(),
										b.editconditionpage1.unit_textfield.getText(),
										b.editconditionpage2.maxtemporalrestriction_textfield.getText(),
										b.editconditionpage2.mintemporalrestriction_textfield.getText(),
										(String) b.editconditionpage2.temporaloperator_combobox.getSelectedItem(),
										(String) b.editconditionpage2.temporalunit_combobox.getSelectedItem());
							} else if (b.editconditionpage1.condition_type == 1) {
								EditCondition((String) a.editconditionpage.list_total.getSelectedValue(),
										b.editconditionpage1.condition_type,
										b.editconditionpage1.qualitativevalue_textfield.getText(),
										(String) b.editconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
										b.editconditionpage1.parameteridentifier_textfield.getText(),
										b.editconditionpage1.conditionparameter_textfield.getText(),
										b.editconditionpage1.unit_textfield.getText(),
										b.editconditionpage2.maxtemporalrestriction_textfield.getText(),
										b.editconditionpage2.mintemporalrestriction_textfield.getText(),
										(String) b.editconditionpage2.temporaloperator_combobox.getSelectedItem(),
										(String) b.editconditionpage2.temporalunit_combobox.getSelectedItem());
							}
						} else if (b.editconditionpage2.temporalrestriction_type == 1) {
							if (b.editconditionpage1.condition_type == 0) {
								EditCondition((String) a.editconditionpage.list_total.getSelectedValue(),
										b.editconditionpage1.condition_type,
										b.editconditionpage1.numericalvalue_textfield.getText(),
										(String) b.editconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
										b.editconditionpage1.parameteridentifier_textfield.getText(),
										b.editconditionpage1.conditionparameter_textfield.getText(),
										b.editconditionpage1.unit_textfield.getText(),
										b.editconditionpage2.temporalrestriction_textfield.getText(),
										(String) b.editconditionpage2.temporaloperator_combobox.getSelectedItem(),
										(String) b.editconditionpage2.temporalunit_combobox.getSelectedItem());
							} else if (b.editconditionpage1.condition_type == 1) {
								EditCondition((String) a.editconditionpage.list_total.getSelectedValue(),
										b.editconditionpage1.condition_type,
										b.editconditionpage1.qualitativevalue_textfield.getText(),
										(String) b.editconditionpage1.comparisonoperator_comboBox.getSelectedItem(),
										b.editconditionpage1.parameteridentifier_textfield.getText(),
										b.editconditionpage1.conditionparameter_textfield.getText(),
										b.editconditionpage1.unit_textfield.getText(),
										b.editconditionpage2.temporalrestriction_textfield.getText(),
										(String) b.editconditionpage2.temporaloperator_combobox.getSelectedItem(),
										(String) b.editconditionpage2.temporalunit_combobox.getSelectedItem());
							}
						}
					}
				}
				// Opcao ClinicalActionType
				else if (a.selectclasspage.choice == 4) {
					if (a.selectclinicalactionpage.choice == 1) { // Exam
						EditExamWizard b = new EditExamWizard(editorkit,
								(String) a.editexampage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditExam((String) a.editexampage.list_total.getSelectedValue(),
									b.editexampage.exam_name_jtext.getText(),
									b.editexampage.exam_description_jtext.getText());
						}
					} else if (a.selectclinicalactionpage.choice == 2) { // Formula
						EditFormulaWizard b = new EditFormulaWizard(editorkit,
								(String) a.editformulapage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditFormula((String) a.editformulapage.list_total.getSelectedValue(),
									b.editformulapage2.formula_description_jtext.getText(),
									(String) b.editformulapage3.list_total.getSelectedValue(),
									b.editformulapage4.model_added);
						}
					} else if (a.selectclinicalactionpage.choice == 3) { // Procedure
						EditProcedureWizard b = new EditProcedureWizard(editorkit,
								(String) a.editprocedurepage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditProcedure((String) a.editprocedurepage.list_total.getSelectedValue(),
									b.editprocedurepage.procedure_name_jtext.getText(),
									b.editprocedurepage.procedure_description_jtext.getText());
						}
					} else if (a.selectclinicalactionpage.choice == 4) { // Medication
																			// Recommendation
						EditMedicationRecommendationWizard b = new EditMedicationRecommendationWizard(editorkit,
								(String) a.editmedicalrecommendationpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditMedicationRecommendation(
									(String) a.editmedicalrecommendationpage.list_total.getSelectedValue(),
									b.editmedicationrecommendationpage.medicationrecommendation_name_jtext.getText(),
									b.editmedicationrecommendationpage.medicationrecommendation_description_jtext
											.getText(),
									b.editmedicationrecommendationpage.medicationrecommendation_activeIngredient_jtext
											.getText(),
									b.editmedicationrecommendationpage.medicationrecommendation_dosage_jtext.getText(),
									b.editmedicationrecommendationpage.medicationrecommendation_pharmaceuticalForm_jtext
											.getText(),
									b.editmedicationrecommendationpage.medicationrecommendation_posology_jtext
											.getText());
						}
					} else if (a.selectclinicalactionpage.choice == 5) { // Non-Medication
																			// Recommendation
						EditNonMedicationRecommendationWizard b = new EditNonMedicationRecommendationWizard(editorkit,
								(String) a.editnonmedicalrecommendationpage.list_total.getSelectedValue());
						code_aux = b.showModalDialog();
						if (code_aux == Wizard.FINISH_RETURN_CODE) {
							EditNonMedicationRecommendation(
									(String) a.editnonmedicalrecommendationpage.list_total.getSelectedValue(),
									b.editnonmedicationrecommendationpage.nonmedicationrecommendation_name_jtext
											.getText(),
									b.editnonmedicationrecommendationpage.nonmedicationrecommendation_description_jtext
											.getText());
						}
					}
				}
				// Opcao Option
				else if (a.selectclasspage.choice == 5) {
					EditOptionWizard b = new EditOptionWizard(editorkit,
							(String) a.editoptionpage.list_total.getSelectedValue());
					code_aux = b.showModalDialog();
					if (code_aux == Wizard.FINISH_RETURN_CODE) {
						EditOption((String) a.editoptionpage.list_total.getSelectedValue(), b.editoptionpage2,
								b.editoptionpage3.model_added);
					}
				}
				// Opcao Parameter
				else if (a.selectclasspage.choice == 6) {
					EditParameterWizard b = new EditParameterWizard(editorkit,
							(String) a.editparameterpage.list_total.getSelectedValue());
					code_aux = b.showModalDialog();
					if (code_aux == Wizard.FINISH_RETURN_CODE) {
						EditParameter((String) a.editparameterpage.list_total.getSelectedValue(), b.editparameterpage);
					}
				}
			}
			recalculate();
			manager.fireEvent(EventType.ACTIVE_ONTOLOGY_CHANGED);
		}
	};
	private JButton deleteButton = new JButton("Delete");
	private ActionListener deleteAction = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			DeleteWizard a = new DeleteWizard(editorkit);
			int code = a.showModalDialog();
			if (code == Wizard.FINISH_RETURN_CODE) {
				// Opcao CPG
				if (a.selectclasspage.choice == 1) {
					deleteCPG(a.deletecpgpage.model_added);
				}
				// Opcao ClinicalTask
				else if (a.selectclasspage.choice == 2) { // Plan Selected
					if (a.selectclinicaltaskpage.choice == 1) {
						deletePlan(a.deleteplanpage.model_added);
					} else if (a.selectclinicaltaskpage.choice == 2) { // Action
						deleteAction(a.deleteactionpage.model_added);
					} else if (a.selectclinicaltaskpage.choice == 3) { // Decision
						deleteDecision(a.deletedecisionpage.model_added);
					} else if (a.selectclinicaltaskpage.choice == 4) { // Question
						deleteQuestion(a.deletequestionpage.model_added);
					} else if (a.selectclinicaltaskpage.choice == 5) { // End
						deleteEnd(a.deleteendpage.model_added);
					}
				}
				// Opcao Condition
				else if (a.selectclasspage.choice == 3) {
					deleteCondition(a.deleteconditionpage.model_added);
				}
				// Opcao ClinicalActionType
				else if (a.selectclasspage.choice == 4) {
					if (a.selectclinicalactionpage.choice == 1) { // Exam
						deleteExam(a.deleteexampage.model_added);
					} else if (a.selectclinicalactionpage.choice == 2) { // Formula
						deleteFormula(a.deleteformulapage.model_added);
					} else if (a.selectclinicalactionpage.choice == 3) { // Procedure
						deleteProcedure(a.deleteprocedurepage.model_added);
					} else if (a.selectclinicalactionpage.choice == 4) { // Medication
																			// Recommendation
						deleteMedicationRecommendation(a.deletemedicalrecommendationpage.model_added);
					} else if (a.selectclinicalactionpage.choice == 5) { // Non-Medication
																			// Recommendation
						deleteNonMedicationRecommendation(a.deletenonmedicalrecommendationpage.model_added);
					}
				}
				// Opcao Option
				else if (a.selectclasspage.choice == 5) {
					deleteOption(a.deleteoptionpage.model_added);
				}
				// Opcao Parameter
				else if (a.selectclasspage.choice == 6) {
					deleteParameter(a.deleteparameterpage.model_added);
				}
			}
			recalculate();
			manager.fireEvent(EventType.ACTIVE_ONTOLOGY_CHANGED);
		}
	};

	private JLabel textComponent_divisor = new JLabel("|");

	private JButton downloadButton = new JButton("Download Ontology");
	private ActionListener downloadAction = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			try {
				String directory_path = System.getProperty("user.dir") + "\\CGuide_Repository";
				String directory_file = System.getProperty("user.dir") + "\\CGuide_Repository\\owl.zip";
				String url = "https://github.com/filipebravo123/CompGuide_Plugin/archive/master.zip";
				
				downloadButton.setEnabled(false);
				ContentDownloader downloader = new ContentDownloader(url,directory_path,directory_file);
				downloader.downloadContents();
				downloadButton.setEnabled(true);
		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	};

	private JButton shareButton = new JButton("Share Ontology");
	private ActionListener shareAction = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//Get Active Ontology URI
	        String uri = (manager.getOntologyPhysicalURI(manager.getActiveOntology())).getPath();
	        
	        ShareWizard a = new ShareWizard(editorkit);
			int code = a.showModalDialog();
			if (code == Wizard.FINISH_RETURN_CODE) {
				TCPClient b = new TCPClient(uri, a.uploadpage.autorship_jtext.getText(), a.uploadpage.guidelineDescription_jtext.getText());
		        try {
		        	manager.save(manager.getActiveOntology());
					b.run();
				} catch (IOException | OWLOntologyStorageException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"A problem has occured on uploading the files. Please check your internet connection or the Ontology file.",
							"OWL file not uploaded successfully.", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	};

	private OWLModelManagerListener modelListener = new OWLModelManagerListener() {

		public void handleChange(OWLModelManagerChangeEvent event) {
			if (event.getType() == EventType.ACTIVE_ONTOLOGY_CHANGED) {
				recalculate();
			}
		}

	};

	private OWLModelManagerListener modelListenerontology = new OWLModelManagerListener() {

		public void handleChange(OWLModelManagerChangeEvent event) {
			if (event.getType() == EventType.ONTOLOGY_LOADED) {
				recalculate();
				updatestate();
			}
		}

	};

	private OWLEditorKit editorkit;
	private OWLModelManager manager;
	private OWLOntologyManager ontology_manager;
	private OWLDataFactory factory;
	private OWLWorkspace workspace;
	private IRI ontologyIRI;

	private IRI Action;
	private IRI CPG;
	private IRI ClinicalSpeciality;
	private IRI Condition;
	private IRI ConditionSet;
	private IRI Decision;
	private IRI Duration;
	private IRI End;
	private IRI Exam;
	private IRI GuidelineCategory;
	private IRI MedicationRecommendation;
	private IRI NonMedicationRecommendation;
	private IRI Option;
	private IRI Outcome;
	private IRI Parameter;
	private IRI Periodicity;
	private IRI Plan;
	private IRI Precondition;
	private IRI Procedure;
	private IRI Question;
	private IRI Scope;
	private IRI TriggerCondition;
	private IRI TemporalRestriction;
	private IRI ClinicalActionType;
	private IRI Formula;

	public Metrics(OWLEditorKit editorkit, OWLModelManager manager, OWLOntologyManager ontology_manager,
			OWLDataFactory factory, OWLWorkspace workspace, IRI ontologyIRI) {
		this.editorkit = editorkit;
		this.manager = manager;
		this.ontology_manager = ontology_manager;
		this.factory = factory;
		this.workspace = workspace;
		this.ontologyIRI = ontologyIRI;

		recalculate();

		manager.addListener(modelListener);
		manager.addListener(modelListenerontology);

		createButton.addActionListener(createAction);
		editButton.addActionListener(editAction);
		deleteButton.addActionListener(deleteAction);
		downloadButton.addActionListener(downloadAction);
		shareButton.addActionListener(shareAction);

		add(textComponent);
		add(createButton);
		add(editButton);
		add(deleteButton);
		add(textComponent_divisor);
		add(downloadButton);
		add(shareButton);

		createButton.setEnabled(false);
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		shareButton.setEnabled(false);

		// Verify if ontology is loaded
		updatestate();

	}

	public void dispose() {
		manager.removeListener(modelListener);
		manager.removeListener(modelListenerontology);

		createButton.removeActionListener(createAction);
		editButton.removeActionListener(editAction);
		deleteButton.removeActionListener(deleteAction);
		downloadButton.removeActionListener(downloadAction);
		shareButton.removeActionListener(shareAction);
	}

	private void recalculate() {
		int count = manager.getActiveOntology().getIndividualsInSignature().size();
		textComponent.setText("Total Individuals = " + count + " |");
	}

	private void deleteCPG(ListModel list) {
		for (int i = 0; i < list.getSize(); i++) {
			String CPG = (String) list.getElementAt(i);

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			OWLNamedIndividual CPG_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + CPG));

			try {
				OWLObjectProperty Scope_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScope"));
				Set<OWLIndividual> Scope_individual_set = CPG_individual.getObjectPropertyValues(Scope_objectproperty,
						manager.getActiveOntology());
				OWLNamedIndividual Scope_individual = Scope_individual_set.iterator().next().asOWLNamedIndividual();

				OWLObjectProperty ConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScopeConditionSet"));
				Set<OWLIndividual> ConditionSet_individual_set = Scope_individual
						.getObjectPropertyValues(ConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual ConditionSet_individual = ConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				ConditionSet_individual.accept(remover);
				Scope_individual.accept(remover);

			} catch (Exception e) {
			}

			// Remove Test Individual and apply changes
			CPG_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Clinical Practice Guidelines deleted successfully.",
				"Clinical Practice Guidelines deleted with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Clinical Practice Guidelines deleted successfully.");

	}

	private void deletePlan(ListModel list) {
		for (int i = 0; i < list.getSize(); i++) {
			String plan = (String) list.getElementAt(i);

			OWLNamedIndividual Plan_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + plan));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			try {
				OWLObjectProperty TriggerCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
				Set<OWLIndividual> TriggerCondition_individual_set = Plan_individual
						.getObjectPropertyValues(TriggerCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerCondition_individual = TriggerCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty TriggerConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));
				Set<OWLIndividual> TriggerConditionSet_individual_set = TriggerCondition_individual
						.getObjectPropertyValues(TriggerConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerConditionSet_individual = TriggerConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				TriggerConditionSet_individual.accept(remover);
				TriggerCondition_individual.accept(remover);
			} catch (Exception e) {
			}

			try {

				OWLObjectProperty PreCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
				Set<OWLIndividual> PreCondition_individual_set = Plan_individual
						.getObjectPropertyValues(PreCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreCondition_individual = PreCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PreConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));
				Set<OWLIndividual> PreConditionSet_individual_set = PreCondition_individual
						.getObjectPropertyValues(PreConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreConditionSet_individual = PreConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PreConditionSet_individual.accept(remover);
				PreCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Outcome_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
				Set<OWLIndividual> Outcome_individual_set = Plan_individual
						.getObjectPropertyValues(Outcome_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Outcome_individual = Outcome_individual_set.iterator().next().asOWLNamedIndividual();

				OWLObjectProperty OutcomeSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcomeConditionSet"));
				Set<OWLIndividual> OutcomeSet_individual_set = Outcome_individual
						.getObjectPropertyValues(OutcomeSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual OutcomeSet_individual = OutcomeSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OutcomeSet_individual.accept(remover);
				Outcome_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Periodicity_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
				Set<OWLIndividual> Periodicity_individual_set = Plan_individual
						.getObjectPropertyValues(Periodicity_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Periodicity_individual = Periodicity_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PeriodicitySet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));
				Set<OWLIndividual> PeriodicitySet_individual_set = Periodicity_individual
						.getObjectPropertyValues(PeriodicitySet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PeriodicitySet_individual = PeriodicitySet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PeriodicitySet_individual.accept(remover);
				Periodicity_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Duration_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));
				Set<OWLIndividual> Duration_individual_set = Plan_individual
						.getObjectPropertyValues(Duration_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Duration_individual = Duration_individual_set.iterator().next()
						.asOWLNamedIndividual();

				Duration_individual.accept(remover);
			} catch (Exception e) {
			}

			Plan_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Plan Tasks deleted successfully.", "Plan Tasks deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Plan Tasks deleted successfully.");
	}

	private void deleteAction(ListModel list) {
		for (int i = 0; i < list.getSize(); i++) {
			String action = (String) list.getElementAt(i);

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			OWLNamedIndividual Action_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + action));

			try {
				OWLObjectProperty TriggerCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
				Set<OWLIndividual> TriggerCondition_individual_set = Action_individual
						.getObjectPropertyValues(TriggerCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerCondition_individual = TriggerCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty TriggerConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));
				Set<OWLIndividual> TriggerConditionSet_individual_set = TriggerCondition_individual
						.getObjectPropertyValues(TriggerConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerConditionSet_individual = TriggerConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				TriggerConditionSet_individual.accept(remover);
				TriggerCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty PreCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
				Set<OWLIndividual> PreCondition_individual_set = Action_individual
						.getObjectPropertyValues(PreCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreCondition_individual = PreCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PreConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));
				Set<OWLIndividual> PreConditionSet_individual_set = PreCondition_individual
						.getObjectPropertyValues(PreConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreConditionSet_individual = PreConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PreConditionSet_individual.accept(remover);
				PreCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Outcome_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
				Set<OWLIndividual> Outcome_individual_set = Action_individual
						.getObjectPropertyValues(Outcome_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Outcome_individual = Outcome_individual_set.iterator().next().asOWLNamedIndividual();

				OWLObjectProperty OutcomeSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcomeConditionSet"));
				Set<OWLIndividual> OutcomeSet_individual_set = Outcome_individual
						.getObjectPropertyValues(OutcomeSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual OutcomeSet_individual = OutcomeSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OutcomeSet_individual.accept(remover);
				Outcome_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Periodicity_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
				Set<OWLIndividual> Periodicity_individual_set = Action_individual
						.getObjectPropertyValues(Periodicity_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Periodicity_individual = Periodicity_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PeriodicitySet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));
				Set<OWLIndividual> PeriodicitySet_individual_set = Periodicity_individual
						.getObjectPropertyValues(PeriodicitySet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PeriodicitySet_individual = PeriodicitySet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PeriodicitySet_individual.accept(remover);
				Periodicity_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty Duration_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));
				Set<OWLIndividual> Duration_individual_set = Action_individual
						.getObjectPropertyValues(Duration_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual Duration_individual = Duration_individual_set.iterator().next()
						.asOWLNamedIndividual();

				Duration_individual.accept(remover);
			} catch (Exception e) {
			}

			// Remove Test Individual and apply changes
			Action_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Action Tasks deleted successfully.", "Action Tasks deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Action Tasks deleted successfully.");
	}

	private void deleteDecision(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String decision = (String) list.getElementAt(i);

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			OWLNamedIndividual Decision_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + decision));

			try {
				OWLObjectProperty TriggerCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
				Set<OWLIndividual> TriggerCondition_individual_set = Decision_individual
						.getObjectPropertyValues(TriggerCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerCondition_individual = TriggerCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty TriggerConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));
				Set<OWLIndividual> TriggerConditionSet_individual_set = TriggerCondition_individual
						.getObjectPropertyValues(TriggerConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerConditionSet_individual = TriggerConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				TriggerConditionSet_individual.accept(remover);
				TriggerCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			try {
				OWLObjectProperty PreCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
				Set<OWLIndividual> PreCondition_individual_set = Decision_individual
						.getObjectPropertyValues(PreCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreCondition_individual = PreCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PreConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));
				Set<OWLIndividual> PreConditionSet_individual_set = PreCondition_individual
						.getObjectPropertyValues(PreConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreConditionSet_individual = PreConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PreConditionSet_individual.accept(remover);
				PreCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			// Remove Test Individual and apply changes
			Decision_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Decision Tasks deleted successfully.",
				"Decision Tasks deleted with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Decision Tasks deleted successfully.");
	}

	private void deleteQuestion(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String question = (String) list.getElementAt(i);

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			OWLNamedIndividual Question_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + question));

			try {
				OWLObjectProperty TriggerCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
				Set<OWLIndividual> TriggerCondition_individual_set = Question_individual
						.getObjectPropertyValues(TriggerCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerCondition_individual = TriggerCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty TriggerConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));
				Set<OWLIndividual> TriggerConditionSet_individual_set = TriggerCondition_individual
						.getObjectPropertyValues(TriggerConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerConditionSet_individual = TriggerConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				TriggerConditionSet_individual.accept(remover);
				TriggerCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			try {

				OWLObjectProperty PreCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
				Set<OWLIndividual> PreCondition_individual_set = Question_individual
						.getObjectPropertyValues(PreCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreCondition_individual = PreCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty PreConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));
				Set<OWLIndividual> PreConditionSet_individual_set = PreCondition_individual
						.getObjectPropertyValues(PreConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual PreConditionSet_individual = PreConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				PreConditionSet_individual.accept(remover);
				PreCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			// Remove Test Individual and apply changes

			Question_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Question Tasks deleted successfully.",
				"Question Tasks deleted with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Question Tasks deleted successfully.");
	}

	private void deleteEnd(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String end = (String) list.getElementAt(i);

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			OWLNamedIndividual End_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + end));

			try {
				OWLObjectProperty TriggerCondition_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
				Set<OWLIndividual> TriggerCondition_individual_set = End_individual
						.getObjectPropertyValues(TriggerCondition_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual TriggerCondition_individual = TriggerCondition_individual_set.iterator().next()
						.asOWLNamedIndividual();

				OWLObjectProperty ConditionSet_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));
				Set<OWLIndividual> ConditionSet_individual_set = TriggerCondition_individual
						.getObjectPropertyValues(ConditionSet_objectproperty, manager.getActiveOntology());
				OWLNamedIndividual ConditionSet_individual = ConditionSet_individual_set.iterator().next()
						.asOWLNamedIndividual();

				ConditionSet_individual.accept(remover);
				TriggerCondition_individual.accept(remover);

			} catch (Exception e) {
			}

			// Remove Test Individual and apply changes

			End_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "End Tasks deleted successfully.", "End Tasks deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("End Tasks deleted successfully.");
	}

	private void deleteExam(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String exam = (String) list.getElementAt(i);

			OWLNamedIndividual Exam_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + exam));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			Exam_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Exams deleted successfully.", "Exams deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Exams deleted successfully.");
	}

	private void deleteFormula(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String formula = (String) list.getElementAt(i);
			OWLNamedIndividual Formula_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + formula));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			Formula_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Formulas deleted successfully.", "Formulas deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Formulas deleted successfully.");
	}

	private void deleteProcedure(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String procedure = (String) list.getElementAt(i);

			OWLNamedIndividual Procedure_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + procedure));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			Procedure_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Procedures deleted successfully.", "Procedures deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Procedures deleted successfully.");
	}

	private void deleteMedicationRecommendation(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String medicationrecommendation = (String) list.getElementAt(i);

			OWLNamedIndividual MedicationRecommendation_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + medicationrecommendation));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			MedicationRecommendation_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Medication Recommendations deleted successfully.",
				"Medication Recommendations deleted with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Medication Recommendations deleted successfully.");
	}

	private void deleteNonMedicationRecommendation(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String nonmedicationrecommendation = (String) list.getElementAt(i);

			OWLNamedIndividual NonMedicationRecommendation_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + nonmedicationrecommendation));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			NonMedicationRecommendation_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}
		JOptionPane.showMessageDialog(null, "Non-Medication Recommendations deleted successfully.",
				"Non-Medication Recommendations deleted with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Non-Medication Recommendations deleted successfully.");
	}

	private void deleteCondition(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String condition = (String) list.getElementAt(i);

			OWLNamedIndividual Condition_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + condition));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			Condition_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Conditions deleted successfully.", "Conditions deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Conditions deleted successfully.");
	}

	private void deleteOption(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String option = (String) list.getElementAt(i);

			OWLNamedIndividual option_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + option));

			OWLObjectProperty ConditionSet_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOptionConditionSet"));
			Set<OWLIndividual> ConditionSet_individual_set = option_individual
					.getObjectPropertyValues(ConditionSet_objectproperty, manager.getActiveOntology());
			OWLNamedIndividual ConditionSet_individual = ConditionSet_individual_set.iterator().next()
					.asOWLNamedIndividual();

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			ConditionSet_individual.accept(remover);
			option_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Options deleted successfully.", "Options deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Options deleted successfully.");
	}

	private void deleteParameter(ListModel list) {

		for (int i = 0; i < list.getSize(); i++) {
			String parameter = (String) list.getElementAt(i);

			OWLNamedIndividual Parameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + parameter));

			// Create OWLEntityRemover
			OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
					Collections.singleton(manager.getActiveOntology()));

			// Remove Test Individual and apply changes
			Parameter_individual.accept(remover);
			ontology_manager.applyChanges(remover.getChanges());
		}

		JOptionPane.showMessageDialog(null, "Parameters deleted successfully.", "Parameters deleted with success",
				JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Parameters deleted successfully.");
	}

	private void CreateCondition(int type_condition, String value, String comparisonOperator,
			String parameterIdentifier, String conditionParameter, String unit, String maxtemporalrestrictionvalue,
			String mintemporalrestrictionvalue, String temporaloperator, String temporalunit) {

		String name_condition = "C" + Integer.toString(getlastindividualnumber(Condition));
		String name_temporalrestriction = "TR" + Integer.toString(getlastindividualnumber(TemporalRestriction));

		OWLClass condition_class = factory.getOWLClass(Condition);
		OWLClass temporalrestriction_class = factory.getOWLClass(TemporalRestriction);

		OWLNamedIndividual condition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_condition));
		OWLNamedIndividual temporalrestriction_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_temporalrestriction));

		// Create Individuals of each OWLClass
		OWLClassAssertionAxiom condition_class_assertion = factory.getOWLClassAssertionAxiom(condition_class,
				condition_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), condition_class_assertion);

		// Both new Conditon and Temporal Restriction were created. Now we must
		// add Data Property to each one

		// Adding DataProperty of Condition
		if (type_condition == 0) { // Condition with numericalValue

			OWLDataProperty numericalValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

			if (!(value.isEmpty())) {
				OWLDatatype numericalValue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalValue_literal = factory.getOWLLiteral(value, numericalValue_type);

				OWLDataPropertyAssertionAxiom numericalValue_assertion = factory.getOWLDataPropertyAssertionAxiom(
						numericalValue_dataproperty, condition_individual, numericalValue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalValue_assertion);
			}

		} else if (type_condition == 1) { // Condition with qualitativeValue
			if (!(value.isEmpty())) {
				OWLDataProperty qualitativeValue_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));
				OWLDataPropertyAssertionAxiom qualitativeValue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(qualitativeValue_dataproperty, condition_individual, value);

				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativeValue_assertion);
			}
		}

		OWLDataProperty parameterIdentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty conditionParameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!parameterIdentifier.isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterIdentifier_assertion = factory.getOWLDataPropertyAssertionAxiom(
					parameterIdentifier_dataproperty, condition_individual, parameterIdentifier);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterIdentifier_assertion);
		}
		if (!conditionParameter.isEmpty()) {
			OWLDataPropertyAssertionAxiom conditionParameter_assertion = factory.getOWLDataPropertyAssertionAxiom(
					conditionParameter_dataproperty, condition_individual, conditionParameter);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionParameter_assertion);
		}
		if (!unit.isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_assertion = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					condition_individual, unit);
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_assertion);
		}

		// Linking ObjectProperty comparisonOperator of Condition
		if (!(comparisonOperator.equals("-"))) {
			OWLNamedIndividual comparisonOperator_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + comparisonOperator));
			OWLObjectProperty comparisonOperator_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));

			OWLObjectPropertyAssertionAxiom comparisonOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					comparisonOperator_objectproperty, condition_individual, comparisonOperator_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), comparisonOperator_assertion);
		}

		// Temporal Restricition
		if (!maxtemporalrestrictionvalue.isEmpty() && !mintemporalrestrictionvalue.isEmpty()) {
			OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
					.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);

			// Adding and Linking ObjectProperty TemporalRestriction of
			// Condition
			OWLObjectProperty temporalrestriction_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

			OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					temporalrestriction_objectproperty, condition_individual, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

			// Adding DataProperty (max Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty maxtemporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxTemporalRestrictionValue"));

			OWLDatatype maxtemporalrestrictionvalue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(maxtemporalrestrictionvalue.isEmpty())) {

				OWLLiteral maxtemporalrestrictionvalue_literal = factory.getOWLLiteral(maxtemporalrestrictionvalue,
						maxtemporalrestrictionvalue_type);

				OWLDataPropertyAssertionAxiom maxtemporalrestrictionvalue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(maxtemporalrestrictionvalue_dataproperty,
								temporalrestriction_individual, maxtemporalrestrictionvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), maxtemporalrestrictionvalue_assertion);
			}

			// Adding DataProperty (min Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty mintemporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minTemporalRestrictionValue"));

			if (!(mintemporalrestrictionvalue.isEmpty())) {
				OWLLiteral mintemporalrestrictionvalue_literal = factory.getOWLLiteral(mintemporalrestrictionvalue,
						maxtemporalrestrictionvalue_type);

				OWLDataPropertyAssertionAxiom mintemporalrestrictionvalue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(mintemporalrestrictionvalue_dataproperty,
								temporalrestriction_individual, mintemporalrestrictionvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), mintemporalrestrictionvalue_assertion);

			}

			// Linking ObjectProperty TemporalOperator of TemporalRestriction
			if (!(temporaloperator.equals("-"))) {
				OWLNamedIndividual temporalOperator_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporaloperator));
				OWLObjectProperty temporalOperator_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalOperator"));

				OWLObjectPropertyAssertionAxiom temporalOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalOperator_objectproperty, temporalrestriction_individual, temporalOperator_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalOperator_assertion);
			}

			// Linking ObjectProperty TemporalUnit of TemporalRestriction
			if (!(temporalunit.equals("-"))) {
				OWLNamedIndividual temporalunit_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporalunit));
				OWLObjectProperty temporalunit_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

				OWLObjectPropertyAssertionAxiom temporalunit_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalunit_objectproperty, temporalrestriction_individual, temporalunit_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_assertion);
			}
		}

		JOptionPane.showMessageDialog(null,
				"Condition was created successfully.\nCondition_ID: " + name_condition + "\nTemporalRestriction_ID: "
						+ name_temporalrestriction,
				"Clinical Condition created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Condition was created successfully.\nCondition_ID: " + name_condition
				+ "\nTemporalRestriction_ID: " + name_temporalrestriction);
	}

	private void CreateCondition(int type_condition, String value, String comparisonOperator,
			String parameterIdentifier, String conditionParameter, String unit, String temporalrestrictionvalue,
			String temporaloperator, String temporalunit) {

		String name_condition = "C" + Integer.toString(getlastindividualnumber(Condition));
		String name_temporalrestriction = "TR" + Integer.toString(getlastindividualnumber(TemporalRestriction));

		OWLClass condition_class = factory.getOWLClass(Condition);
		OWLClass temporalrestriction_class = factory.getOWLClass(TemporalRestriction);

		OWLNamedIndividual condition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_condition));
		OWLNamedIndividual temporalrestriction_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_temporalrestriction));

		// Create Individuals of each OWLClass
		OWLClassAssertionAxiom condition_class_assertion = factory.getOWLClassAssertionAxiom(condition_class,
				condition_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), condition_class_assertion);

		// Both new Conditon and Temporal Restriction were created. Now we must
		// add Data Property to each one

		// Adding DataProperty of Condition
		if (type_condition == 0) { // Condition with numericalValue

			OWLDataProperty numericalValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

			if (!(value.isEmpty())) {
				OWLDatatype numericalValue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalValue_literal = factory.getOWLLiteral(value, numericalValue_type);

				OWLDataPropertyAssertionAxiom numericalValue_assertion = factory.getOWLDataPropertyAssertionAxiom(
						numericalValue_dataproperty, condition_individual, numericalValue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalValue_assertion);
			}

		} else if (type_condition == 1) { // Condition with qualitativeValue
			if (!(value.isEmpty())) {
				OWLDataProperty qualitativeValue_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));

				OWLDataPropertyAssertionAxiom qualitativeValue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(qualitativeValue_dataproperty, condition_individual, value);
				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativeValue_assertion);
			}
		}

		OWLDataProperty parameterIdentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty conditionParameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!parameterIdentifier.isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterIdentifier_assertion = factory.getOWLDataPropertyAssertionAxiom(
					parameterIdentifier_dataproperty, condition_individual, parameterIdentifier);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterIdentifier_assertion);
		}
		if (!conditionParameter.isEmpty()) {
			OWLDataPropertyAssertionAxiom conditionParameter_assertion = factory.getOWLDataPropertyAssertionAxiom(
					conditionParameter_dataproperty, condition_individual, conditionParameter);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionParameter_assertion);
		}
		if (!unit.isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_assertion = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					condition_individual, unit);
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_assertion);
		}

		// Linking ObjectProperty comparisonOperator of Condition
		if (!(comparisonOperator.equals("-"))) {
			OWLNamedIndividual comparisonOperator_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + comparisonOperator));
			OWLObjectProperty comparisonOperator_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));

			OWLObjectPropertyAssertionAxiom comparisonOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					comparisonOperator_objectproperty, condition_individual, comparisonOperator_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), comparisonOperator_assertion);
		}

		// Temporal Restriction
		if (!(temporalrestrictionvalue.isEmpty())) {
			OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
					.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);

			// Adding and Linking ObjectProperty TemporalRestriction of
			// Condition
			OWLObjectProperty temporalrestriction_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

			OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					temporalrestriction_objectproperty, condition_individual, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

			// Adding DataProperty (Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty temporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#temporalRestrictionValue"));

			OWLDatatype temporalrestrictionvalue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
			OWLLiteral temporalrestrictionvalue_literal = factory.getOWLLiteral(temporalrestrictionvalue,
					temporalrestrictionvalue_type);

			OWLDataPropertyAssertionAxiom temporalrestrictionvalue_assertion = factory.getOWLDataPropertyAssertionAxiom(
					temporalrestrictionvalue_dataproperty, temporalrestriction_individual,
					temporalrestrictionvalue_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestrictionvalue_assertion);

			// Linking ObjectProperty TemporalOperator of TemporalRestriction
			if (!(temporaloperator.equals("-"))) {
				OWLNamedIndividual temporalOperator_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporaloperator));
				OWLObjectProperty temporalOperator_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalOperator"));

				OWLObjectPropertyAssertionAxiom temporalOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalOperator_objectproperty, temporalrestriction_individual, temporalOperator_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalOperator_assertion);
			}

			// Linking ObjectProperty TemporalUnit of TemporalRestriction
			if (!(temporalunit.equals("-"))) {
				OWLNamedIndividual temporalunit_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporalunit));
				OWLObjectProperty temporalunit_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

				OWLObjectPropertyAssertionAxiom temporalunit_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalunit_objectproperty, temporalrestriction_individual, temporalunit_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_assertion);
			}
		}

		JOptionPane.showMessageDialog(null,
				"Condition was created successfully.\nCondition_ID: " + name_condition + "\nTemporalRestriction_ID: "
						+ name_temporalrestriction,
				"Clinical Condition created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Condition was created successfully.\nCondition_ID: " + name_condition
				+ "\nTemporalRestriction_ID: " + name_temporalrestriction);

	}

	private void CreateCPG(String guidelinename, String author, String description, ListModel disease,
			ListModel intended, ListModel target, ListModel speciality, ListModel category, ListModel conditions,
			String plan) {

		String name_cpg = "CPG" + Integer.toString(getlastindividualnumber(CPG));
		String name_scope = "Scope" + Integer.toString(getlastindividualnumber(Scope));
		String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

		OWLClass ClinicalPracticeGuideline = factory.getOWLClass(CPG);

		// Create Individual Test
		OWLNamedIndividual cpg = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_cpg));

		// Link Individual cpg to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(ClinicalPracticeGuideline, cpg);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// Link the OWLClass with the OWL data property.
		OWLDataProperty guidename = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#guidelineName"));
		OWLDataProperty authorship = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#authorship"));
		OWLDataProperty guidedescription = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#guidelineDescription"));
		OWLDataProperty dateofcreation = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dateOfCreation"));
		OWLDataProperty dateoflastupdate = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dateOfLastUpdate"));
		OWLDataProperty versionnumber = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#versionNumber"));

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();

		String date = df.format(dateobj).toString() + "T" + df1.format(dateobj).toString();

		OWLDatatype dateTimeType = factory.getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
		OWLLiteral dateLeve = factory.getOWLLiteral(date, dateTimeType);

		OWLDatatype versionType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
		OWLLiteral version = factory.getOWLLiteral("1.0", versionType);

		// Create the actual assertion (triple), as an object property assertion
		// axiom
		if (!guidelinename.isEmpty()) {
			OWLDataPropertyAssertionAxiom guidename_assertion = factory.getOWLDataPropertyAssertionAxiom(guidename, cpg,
					guidelinename);
			ontology_manager.addAxiom(manager.getActiveOntology(), guidename_assertion);
		}
		if (!author.isEmpty()) {
			OWLDataPropertyAssertionAxiom autorship_assertion = factory.getOWLDataPropertyAssertionAxiom(authorship,
					cpg, author);
			ontology_manager.addAxiom(manager.getActiveOntology(), autorship_assertion);
		}
		if (!description.isEmpty()) {
			OWLDataPropertyAssertionAxiom guidedescription_assertion = factory
					.getOWLDataPropertyAssertionAxiom(guidedescription, cpg, description);
			ontology_manager.addAxiom(manager.getActiveOntology(), guidedescription_assertion);
		}

		OWLDataPropertyAssertionAxiom dateofcreation_assertion = factory
				.getOWLDataPropertyAssertionAxiom(dateofcreation, cpg, dateLeve);
		OWLDataPropertyAssertionAxiom dateoflastupdate_assertion = factory
				.getOWLDataPropertyAssertionAxiom(dateoflastupdate, cpg, dateLeve);
		OWLDataPropertyAssertionAxiom versionnumber_assertion = factory.getOWLDataPropertyAssertionAxiom(versionnumber,
				cpg, version);

		// Add Assertion Axiom to the OWL file
		ontology_manager.addAxiom(manager.getActiveOntology(), versionnumber_assertion);
		ontology_manager.addAxiom(manager.getActiveOntology(), dateoflastupdate_assertion);
		ontology_manager.addAxiom(manager.getActiveOntology(), dateofcreation_assertion);

		// Add Scope (OWL Object Property)
		if (disease.getSize() + intended.getSize() + target.getSize() + speciality.getSize() + category.getSize() > 0) {

			OWLClass scope = factory.getOWLClass(Scope);
			// Create Scope Individual
			OWLNamedIndividual scope_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_scope));
			// Link Individual scope to OWLClass
			OWLClassAssertionAxiom scope_classAssertion = factory.getOWLClassAssertionAxiom(scope, scope_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), scope_classAssertion);

			// Add Data Property to Scope
			OWLDataProperty disease_condition = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#diseaseOrCondition"));
			OWLDataProperty intended_user = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#intendedUser"));
			OWLDataProperty target_population = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#targetPopulation"));

			for (int i = 0; i < disease.getSize(); i++) {
				OWLDataPropertyAssertionAxiom disease_condition_assertion = factory.getOWLDataPropertyAssertionAxiom(
						disease_condition, scope_individual, (String) disease.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), disease_condition_assertion);
			}

			for (int i = 0; i < intended.getSize(); i++) {
				OWLDataPropertyAssertionAxiom intended_user_assertion = factory.getOWLDataPropertyAssertionAxiom(
						intended_user, scope_individual, (String) intended.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), intended_user_assertion);
			}

			for (int i = 0; i < target.getSize(); i++) {
				OWLDataPropertyAssertionAxiom target_population_assertion = factory.getOWLDataPropertyAssertionAxiom(
						target_population, scope_individual, (String) target.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), target_population_assertion);
			}

			// Add Object Property to Scope (Clinical Speciality)
			OWLObjectProperty hasclinicalspecialty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasClinicalSpecialty"));
			for (int i = 0; i < speciality.getSize(); i++) {
				OWLNamedIndividual speciality_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + speciality.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom clinical_speciality_assertion = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalspecialty, scope_individual,
								speciality_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), clinical_speciality_assertion);
			}

			// Add Object Property to Scope (Guideline Category)
			OWLObjectProperty hasguidelinecategory = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasGuidelineCategory"));
			for (int i = 0; i < category.getSize(); i++) {
				OWLNamedIndividual category_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + category.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom category_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasguidelinecategory, scope_individual, category_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), category_assertion);
			}

			// Add New ConditionSet (OWL Object Property)
			if (conditions.getSize() > 0) {
				OWLClass conditionset = factory.getOWLClass(ConditionSet);
				// Create ConditionSet Individual
				OWLNamedIndividual conditionset_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));
				// Link Individual conditionset to OWLClass
				OWLClassAssertionAxiom conditionset_classAssertion = factory.getOWLClassAssertionAxiom(conditionset,
						conditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_classAssertion);

				// Add Object Property to Scope (ConditionSet)
				OWLObjectProperty hasCondition = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));
				for (int i = 0; i < conditions.getSize(); i++) {
					OWLNamedIndividual condition_individual = factory
							.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + conditions.getElementAt(i)));
					OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
							hasCondition, conditionset_individual, condition_individual);
					ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
				}
				OWLDataProperty conditionsetcounter_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));

				OWLDatatype conditionsetcounterType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
				OWLLiteral conditionsetcounter_literal = factory.getOWLLiteral(Integer.toString(conditions.getSize()),
						conditionsetcounterType);

				// Create the actual assertion (triple), as an object property
				// assertion
				// axiom
				OWLDataPropertyAssertionAxiom conditionsetcounter_assertion = factory.getOWLDataPropertyAssertionAxiom(
						conditionsetcounter_dataproperty, conditionset_individual, conditionsetcounter_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_assertion);

				// ConditionSet finished Creating. Now Link Scope to
				// ConditionSet.
				OWLObjectProperty hasscopeconditionset = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScopeConditionSet"));
				OWLObjectPropertyAssertionAxiom scopeconditionset_assertion = factory
						.getOWLObjectPropertyAssertionAxiom(hasscopeconditionset, scope_individual,
								conditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), scopeconditionset_assertion);
			}

			// Link the OWLClass with the OWL data property.
			OWLObjectProperty hasScope = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScope"));
			OWLObjectPropertyAssertionAxiom cpg_scope = factory.getOWLObjectPropertyAssertionAxiom(hasScope, cpg,
					scope_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), cpg_scope);
		}

		// Scope finished creating. Now let's associate the selected Plan
		// Add Plan
		if (!(plan.equals("-"))) {
			OWLNamedIndividual plan_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + plan));
			OWLObjectProperty hasPlan = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPlan"));
			OWLObjectPropertyAssertionAxiom cpg_plan = factory.getOWLObjectPropertyAssertionAxiom(hasPlan, cpg,
					plan_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), cpg_plan);
		}

		JOptionPane.showMessageDialog(null,
				"CPG_ID: " + name_cpg + " was successfully created.",
				"Clinical Practice Guideline created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Clinical Practice Guideline was successfully created.\nCPG_ID: " + name_cpg);
	}

	private void CreateAction(String description, CreateActionPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions, String stop_clinicaltask, ListModel preconditions,
			ListModel outcomeconditions, ListModel triggerconditions, int choice, ListModel exam, ListModel formula,
			ListModel procedure, ListModel medicalrecommendation, ListModel nonmedicalrecommendation,
			CreateActionPage10 duration_restrictions, String sync_clinicaltask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {

		String name_action = "Action" + Integer.toString(getlastindividualnumber(Action));
		String name_periodicity = "Periodicity" + Integer.toString(getlastindividualnumber(Periodicity));
		String name_outcome = "O" + Integer.toString(getlastindividualnumber(Outcome));
		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		String name_duration = "Duration" + Integer.toString(getlastindividualnumber(Duration));

		OWLClass action_class = factory.getOWLClass(Action);

		// Create Individual
		OWLNamedIndividual action_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_action));

		// Link Individual to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(action_class, action_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// DataProperty Generaldescription
		generalDescription(action_individual, description);

		// Create ObjectProperty Individuals
		OWLClass periodicity_class = factory.getOWLClass(Periodicity);
		OWLClass outcome_class = factory.getOWLClass(Outcome);
		OWLClass precondition_class = factory.getOWLClass(Precondition);
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);
		OWLClass duration_class = factory.getOWLClass(Duration);

		OWLNamedIndividual periodicity_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_periodicity));
		OWLNamedIndividual outcome_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_outcome));
		OWLNamedIndividual precondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
		OWLNamedIndividual triggercondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));
		OWLNamedIndividual duration_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_duration));

		OWLObjectProperty periodicity_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
		OWLObjectProperty outcome_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty duration_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));

		// ---------------

		// Link the OWLClass with the OWL Object property.

		// Handle periodicity
		if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
				&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
				|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {
			OWLClassAssertionAxiom periodicity_classaxium = factory.getOWLClassAssertionAxiom(periodicity_class,
					periodicity_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_classaxium);
			OWLObjectPropertyAssertionAxiom periodicity_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(periodicity_objectproperty, action_individual,
							periodicity_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_objectpropertyaxiom);
			periodicity(periodicity_individual, periodicity_restrictions, periodicity_stop_restrictions);
		}

		// stopConditionTask
		stopconditiontask(action_individual, stop_clinicaltask);

		// syncTask
		synctask(action_individual, sync_clinicaltask);

		// hasOutcome
		if (outcomeconditions.getSize() > 0) {
			OWLClassAssertionAxiom outcome_classaxium = factory.getOWLClassAssertionAxiom(outcome_class,
					outcome_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), outcome_classaxium);
			OWLObjectPropertyAssertionAxiom outcome_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(outcome_objectproperty, action_individual, outcome_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), outcome_objectpropertyaxiom);
			outcomecondition(outcome_individual, outcomeconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			OWLClassAssertionAxiom precondition_classaxium = factory.getOWLClassAssertionAxiom(precondition_class,
					precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxium);
			OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, action_individual,
							precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			precondition(precondition_individual, preconditions);
		}

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			OWLClassAssertionAxiom triggercondition_classaxium = factory
					.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxium);
			OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, action_individual,
							triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// ClinicalActionType
		clinicalactiontype(action_individual, choice, exam, formula, procedure, medicalrecommendation,
				nonmedicalrecommendation);

		// Duration
		if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
				&& !duration_restrictions.minduration_textfield.getText().isEmpty())
				|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
			OWLClassAssertionAxiom duration_classaxium = factory.getOWLClassAssertionAxiom(duration_class,
					duration_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), duration_classaxium);
			OWLObjectPropertyAssertionAxiom duration_objectpropertyaxiom = factory.getOWLObjectPropertyAssertionAxiom(
					duration_objectproperty, action_individual, duration_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), duration_objectpropertyaxiom);
			duration(duration_individual, duration_restrictions);
		}

		// Nexttask
		nexttask(action_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Action_ID: " + name_action + " was successfully created.",
				"Action created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Action created with success.\nAction_ID: " + name_action);
	}

	private void CreatePlan(String description, CreatePlanPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions, String stop_clinicaltask, ListModel preconditions,
			ListModel outcomeconditions, ListModel triggerconditions, ListModel firsttasks,
			CreatePlanPage10 duration_restrictions, String sync_clinicaltask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {

		String name_plan = "Plan" + Integer.toString(getlastindividualnumber(Plan));
		String name_periodicity = "Periodicity" + Integer.toString(getlastindividualnumber(Periodicity));
		String name_outcome = "O" + Integer.toString(getlastindividualnumber(Outcome));
		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		String name_duration = "Duration" + Integer.toString(getlastindividualnumber(Duration));

		OWLClass plan_class = factory.getOWLClass(Plan);

		// Create Individual
		OWLNamedIndividual plan_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_plan));

		// Link Individual to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(plan_class, plan_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// DataProperty Generaldescription
		generalDescription(plan_individual, description);

		// Create ObjectProperty Individuals
		OWLClass periodicity_class = factory.getOWLClass(Periodicity);
		OWLClass outcome_class = factory.getOWLClass(Outcome);
		OWLClass precondition_class = factory.getOWLClass(Precondition);
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);
		OWLClass duration_class = factory.getOWLClass(Duration);

		OWLNamedIndividual periodicity_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_periodicity));
		OWLNamedIndividual outcome_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_outcome));
		OWLNamedIndividual precondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
		OWLNamedIndividual triggercondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));
		OWLNamedIndividual duration_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_duration));

		OWLObjectProperty outcome_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty duration_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));

		// -------

		// Link the OWLClass with the OWL Object property.

		// periodicity
		if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
				&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
				|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {

			OWLClassAssertionAxiom periodicity_classaxiom = factory.getOWLClassAssertionAxiom(periodicity_class,
					periodicity_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_classaxiom);
			OWLObjectProperty periodicity_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
			OWLObjectPropertyAssertionAxiom periodicity_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(periodicity_objectproperty, plan_individual,
							periodicity_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_objectpropertyaxiom);
			periodicity(periodicity_individual, periodicity_restrictions, periodicity_stop_restrictions);
		}

		// stopConditionTask
		stopconditiontask(plan_individual, stop_clinicaltask);

		// syncTask
		synctask(plan_individual, sync_clinicaltask);

		// hasOutcome
		if (outcomeconditions.getSize() > 0) {
			OWLClassAssertionAxiom outcome_classaxiom = factory.getOWLClassAssertionAxiom(outcome_class,
					outcome_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), outcome_classaxiom);
			OWLObjectPropertyAssertionAxiom outcome_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(outcome_objectproperty, plan_individual, outcome_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), outcome_objectpropertyaxiom);
			outcomecondition(outcome_individual, outcomeconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
					precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxiom);
			OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, plan_individual,
							precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			precondition(precondition_individual, preconditions);
		}

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			OWLClassAssertionAxiom triggercondition_classaxiom = factory
					.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxiom);
			OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, plan_individual,
							triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// HasFirstTask
		firsttask(plan_individual, firsttasks);

		// Duration
		if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
				&& !duration_restrictions.minduration_textfield.getText().isEmpty())
				|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
			OWLClassAssertionAxiom duration_classaxiom = factory.getOWLClassAssertionAxiom(duration_class,
					duration_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), duration_classaxiom);
			OWLObjectPropertyAssertionAxiom duration_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(duration_objectproperty, plan_individual, duration_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), duration_objectpropertyaxiom);
			duration(duration_individual, duration_restrictions);
		}

		// Nexttask
		nexttask(plan_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Plan_ID: " + name_plan + " was successfully created.",
				"Plan created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Plan created with success.\nPlan_ID: " + name_plan);
	}

	private void CreateEnd(String description, ListModel triggerconditions) {
		String name_end = "End" + Integer.toString(getlastindividualnumber(End));
		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));

		OWLClass end_class = factory.getOWLClass(End);

		// Create Individual
		OWLNamedIndividual end_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_end));

		// Link Individual to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(end_class, end_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// DataProperty Generaldescription
		generalDescription(end_individual, description);

		// TriggerCondition Constructor
		if (triggerconditions.getSize() > 0) {
			OWLClass trigger_class = factory.getOWLClass(TriggerCondition);

			OWLObjectProperty triggercondition_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));

			OWLNamedIndividual triggercondition_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

			OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
					triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), trigger_classaxiom);

			OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					triggercondition_objectproperty, end_individual, triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_axiom);

			// TriggerCondition
			triggercondition(triggercondition_individual, triggerconditions);
		}

		JOptionPane.showMessageDialog(null, "End_ID: " + name_end + " was successfully created.",
				"End created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("End created with success.\nEnd_ID: " + name_end);

	}

	private void CreateDecision(String description, ListModel preconditions, ListModel triggerconditions,
			ListModel options, String synctask, int nexttype, ListModel alternativetask, String next,
			ListModel parallel, ListModel preferencealternative) {

		String name_decision = "Decision" + Integer.toString(getlastindividualnumber(Decision));
		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));

		OWLClass decision_class = factory.getOWLClass(Decision);

		// Create Individual
		OWLNamedIndividual decision_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_decision));

		// Link Individual to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(decision_class, decision_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// DataProperty generalDescription
		generalDescription(decision_individual, description);

		// Create ObjectProperty Individuals
		OWLClass precondition_class = factory.getOWLClass(Precondition);
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);

		OWLNamedIndividual precondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
		OWLNamedIndividual triggercondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));

		// ---------------------

		// Link the OWLClass with the OWL Object property.

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			OWLClassAssertionAxiom triggercondition_classaxium = factory
					.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxium);
			OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, decision_individual,
							triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			OWLClassAssertionAxiom precondition_classaxium = factory.getOWLClassAssertionAxiom(precondition_class,
					precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxium);
			OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, decision_individual,
							precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			precondition(precondition_individual, preconditions);
		}

		// SyncTask
		synctask(decision_individual, synctask);

		// Option
		OWLObjectProperty option_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOption"));
		for (int i = 0; i < options.getSize(); i++) {
			OWLNamedIndividual option_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + options.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom option_axiom = factory
					.getOWLObjectPropertyAssertionAxiom(option_objectproperty, decision_individual, option_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), option_axiom);
		}

		// Next task
		nexttask(decision_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Decision_ID: " + name_decision + " was successfully created.",
				"Decision created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Decision created with success.\nDecision_ID: " + name_decision);

	}

	private void CreateQuestion(String description, ListModel preconditions, ListModel triggerconditions,
			ListModel parameters, String synctask, int nexttype, ListModel alternativetask, String next,
			ListModel parallel, ListModel preferencealternative) {

		String name_question = "Question" + Integer.toString(getlastindividualnumber(Question));
		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));

		OWLClass question_class = factory.getOWLClass(Question);

		// Create Individual
		OWLNamedIndividual question_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_question));

		// Link Individual to OWLClass
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(question_class, question_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), classAssertion);

		// DataProperty generalDescription
		generalDescription(question_individual, description);

		// Create ObjectProperty Individuals
		OWLClass precondition_class = factory.getOWLClass(Precondition);
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);

		OWLNamedIndividual precondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
		OWLNamedIndividual triggercondition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));

		// ---------------------

		// Link the OWLClass with the OWL Object property.

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			OWLClassAssertionAxiom triggercondition_classaxium = factory
					.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxium);
			OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, question_individual,
							triggercondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			OWLClassAssertionAxiom precondition_classaxium = factory.getOWLClassAssertionAxiom(precondition_class,
					precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxium);
			OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, question_individual,
							precondition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			precondition(precondition_individual, preconditions);
		}

		// SyncTask
		synctask(question_individual, synctask);

		// Parameter
		OWLObjectProperty parameter_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasParameter"));
		for (int i = 0; i < parameters.getSize(); i++) {
			OWLNamedIndividual parameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + parameters.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom parameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					parameter_objectproperty, question_individual, parameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameter_axiom);
		}

		// Next task
		nexttask(question_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Question_ID: " + name_question + " was successfully created.",
				"Question created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Question created with success.\nQuestion_ID: " + name_question);
	}

	private void CreateParameter(CreateParameterPage createparameterpage) {
		// Parameter
		String name_parameter = "Parameter" + Integer.toString(getlastindividualnumber(Parameter));
		OWLClass parameter_class = factory.getOWLClass(Parameter);
		OWLNamedIndividual parameter_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_parameter));
		OWLClassAssertionAxiom parameter_axiom = factory.getOWLClassAssertionAxiom(parameter_class,
				parameter_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), parameter_axiom);

		// Data Property
		OWLDataProperty possiblevalue_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#possibleValue"));
		OWLDataProperty variablename_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#variableName"));
		OWLDataProperty numeric_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#numeric"));
		OWLDataProperty parameteridentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty questionparameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#questionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));
		OWLDataProperty parameterdescription_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterDescription"));

		// numericValue
		OWLDatatype BooleanType = factory.getOWLDatatype(OWL2Datatype.XSD_BOOLEAN.getIRI());
		String aux = createparameterpage.possiblevalue_textField.getText();
		aux = aux.replaceAll("\\D+", "");
		OWLLiteral numeric_literal;
		if (aux.isEmpty()) {
			numeric_literal = factory.getOWLLiteral("false", BooleanType);
		} else {
			numeric_literal = factory.getOWLLiteral("true", BooleanType);
		}

		if (!createparameterpage.possiblevalue_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom possiblevalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					possiblevalue_dataproperty, parameter_individual,
					createparameterpage.possiblevalue_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), possiblevalue_axiom);
			OWLDataPropertyAssertionAxiom numeric_axiom = factory.getOWLDataPropertyAssertionAxiom(numeric_dataproperty,
					parameter_individual, numeric_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), numeric_axiom);
		}
		if (!createparameterpage.variablename_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom variablename_axiom = factory.getOWLDataPropertyAssertionAxiom(
					variablename_dataproperty, parameter_individual,
					createparameterpage.variablename_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), variablename_axiom);
		}
		if (!createparameterpage.parameteridentifier_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameteridentifier_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameteridentifier_dataproperty, parameter_individual,
					createparameterpage.parameteridentifier_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameteridentifier_axiom);
		}
		if (!createparameterpage.questionparameter_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom questionparameter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					questionparameter_dataproperty, parameter_individual,
					createparameterpage.questionparameter_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), questionparameter_axiom);
		}
		if (!createparameterpage.unit_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_axiom = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					parameter_individual, createparameterpage.unit_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_axiom);
		}
		if (!createparameterpage.parameterdescription_textArea.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterdescription_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameterdescription_dataproperty, parameter_individual,
					createparameterpage.parameterdescription_textArea.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterdescription_axiom);
		}

		JOptionPane.showMessageDialog(null, "Parameter_ID: " + name_parameter + " was successfully created.",
				"Parameter created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Parameter created with success.\nParameter_ID: " + name_parameter);
	}

	private void CreateOption(CreateOptionPage2 createoptionpage2, ListModel optionconditionset) {
		// Option
		String name_option = "Op" + Integer.toString(getlastindividualnumber(Option));
		OWLClass option_class = factory.getOWLClass(Option);
		OWLNamedIndividual option_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_option));
		OWLClassAssertionAxiom option_axiom = factory.getOWLClassAssertionAxiom(option_class, option_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), option_axiom);

		// Data Property
		if (createoptionpage2.option_type == 0) { // NumericalValue ->
													// option_type = 0

			if (!(createoptionpage2.numericalvalue_textField.getText().isEmpty())) {
				OWLDataProperty numericalvalue_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

				OWLDatatype Decimal = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalvalue_literal = factory
						.getOWLLiteral(createoptionpage2.numericalvalue_textField.getText(), Decimal);

				OWLDataPropertyAssertionAxiom numericalvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						numericalvalue_dataproperty, option_individual, numericalvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalvalue_axiom);
			}

		} else { // QualitativeValue -> option_type = 1
			if (!createoptionpage2.qualitativevalue_textField.getText().isEmpty()) {
				OWLDataProperty qualitativevalue_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));
				OWLDataPropertyAssertionAxiom qualitativevalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						qualitativevalue_dataproperty, option_individual,
						createoptionpage2.qualitativevalue_textField.getText());

				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativevalue_axiom);
			}
		}
		OWLDataProperty parameteridentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty optionparameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#optionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!createoptionpage2.parameteridentifier_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameteridentifier_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameteridentifier_dataproperty, option_individual,
					createoptionpage2.parameteridentifier_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameteridentifier_axiom);
		}
		if (!createoptionpage2.optionparameter_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom optionparameter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					optionparameter_dataproperty, option_individual,
					createoptionpage2.optionparameter_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), optionparameter_axiom);
		}
		if (!createoptionpage2.unit_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_axiom = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					option_individual, createoptionpage2.unit_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_axiom);
		}

		// Object Property - Option ConditionSet
		if (optionconditionset.getSize() > 0) {
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));
			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			OWLNamedIndividual optionconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			OWLClassAssertionAxiom conditionset_axiom = factory.getOWLClassAssertionAxiom(conditionset_class,
					optionconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_axiom);

			OWLObjectProperty hasoptionconditionset_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOptionConditionSet"));

			OWLObjectPropertyAssertionAxiom optionconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasoptionconditionset_objectproperty, option_individual, optionconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), optionconditionset_axiom);

			OWLObjectProperty hasCondition_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

			for (int i = 0; i < optionconditionset.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + optionconditionset.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, optionconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			// OptionConditionSetCounter
			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(optionconditionset.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, optionconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		JOptionPane.showMessageDialog(null, "Option_ID: " + name_option + " was successfully created.",
				"Option created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Option created with success.\nOption_ID: " + name_option);
	}

	private void CreateExam(String examName, String examDescription) {
		// Exam
		String name_exam = "Exam" + Integer.toString(getlastindividualnumber(Exam));
		OWLClass exam_class = factory.getOWLClass(Exam);
		OWLNamedIndividual exam_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_exam));
		OWLClassAssertionAxiom exam_classaxiom = factory.getOWLClassAssertionAxiom(exam_class, exam_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), exam_classaxiom);

		if (!examDescription.isEmpty()) {
			OWLDataProperty examDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#examDescription"));
			OWLDataPropertyAssertionAxiom examDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(examDescription_dataproperty, exam_individual, examDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), examDescription_axiom);
		}

		if (!examName.isEmpty()) {
			OWLDataProperty examName_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#examName"));
			OWLDataPropertyAssertionAxiom examName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(examName_dataproperty, exam_individual, examName);
			ontology_manager.addAxiom(manager.getActiveOntology(), examName_axiom);
		}

		JOptionPane.showMessageDialog(null, "Exam_ID: " + name_exam + " was successfully created.",
				"Exam created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Exam created with success.\nOption_ID: " + name_exam);
	}

	private void CreateProcedure(String procedureName, String procedureDescription) {
		// Procedure
		String name_procedure = "Procedure" + Integer.toString(getlastindividualnumber(Procedure));
		OWLClass procedure_class = factory.getOWLClass(Procedure);
		OWLNamedIndividual procedure_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_procedure));
		OWLClassAssertionAxiom procedure_classaxiom = factory.getOWLClassAssertionAxiom(procedure_class,
				procedure_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), procedure_classaxiom);

		if (!procedureDescription.isEmpty()) {
			OWLDataProperty procedureDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#procedureDescription"));
			OWLDataPropertyAssertionAxiom procedureDescription_axiom = factory.getOWLDataPropertyAssertionAxiom(
					procedureDescription_dataproperty, procedure_individual, procedureDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), procedureDescription_axiom);
		}

		if (!procedureName.isEmpty()) {
			OWLDataProperty procedureName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#procedureName"));
			OWLDataPropertyAssertionAxiom procedureName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(procedureName_dataproperty, procedure_individual, procedureName);
			ontology_manager.addAxiom(manager.getActiveOntology(), procedureName_axiom);
		}

		JOptionPane.showMessageDialog(null, "Procedure_ID: " + name_procedure + " was successfully created.",
				"Procedure created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Procedure created with success.\nProcedure_ID: " + name_procedure);
	}

	private void CreateNonMedicationRecommendation(String nonMedicationRecommendationName,
			String nonMedicationRecommendationDescription) {
		// NonMedicationRecommendation
		String name_nonmedicationrecommendation = "NonMedicationRecommendation"
				+ Integer.toString(getlastindividualnumber(NonMedicationRecommendation));
		OWLClass nonmedicationrecommendation_class = factory.getOWLClass(NonMedicationRecommendation);
		OWLNamedIndividual nonmedicationrecommendation_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_nonmedicationrecommendation));
		OWLClassAssertionAxiom nonmedicationrecommendation_classaxiom = factory
				.getOWLClassAssertionAxiom(nonmedicationrecommendation_class, nonmedicationrecommendation_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), nonmedicationrecommendation_classaxiom);

		if (!nonMedicationRecommendationDescription.isEmpty()) {
			OWLDataProperty nonMedicationRecommendationDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#nonMedicationRecommendationDescription"));
			OWLDataPropertyAssertionAxiom nonMedicationRecommendationDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(nonMedicationRecommendationDescription_dataproperty,
							nonmedicationrecommendation_individual, nonMedicationRecommendationDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), nonMedicationRecommendationDescription_axiom);
		}
		if (!nonMedicationRecommendationName.isEmpty()) {
			OWLDataProperty nonMedicationRecommendationName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#nonMedicationRecommendationName"));
			OWLDataPropertyAssertionAxiom nonMedicationRecommendationName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(nonMedicationRecommendationName_dataproperty,
							nonmedicationrecommendation_individual, nonMedicationRecommendationName);
			ontology_manager.addAxiom(manager.getActiveOntology(), nonMedicationRecommendationName_axiom);
		}

		JOptionPane.showMessageDialog(null,
				"Non-Medication Recommendation_ID: " + name_nonmedicationrecommendation + " was successfully created.",
				"Non-Medication Recommendation created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println(
				"Non-Medication Recommendation created with success.\nOption_ID: " + name_nonmedicationrecommendation);
	}

	private void CreateMedicationRecommendation(String MedicationRecommendationName,
			String MedicationRecommendationDescription, String activeIngredient, String dosage,
			String pharmaceuticalForm, String posology) {
		// MedicationRecommendation
		String name_medicationrecommendation = "MedicationRecommendation"
				+ Integer.toString(getlastindividualnumber(MedicationRecommendation));
		OWLClass medicationrecommendation_class = factory.getOWLClass(MedicationRecommendation);
		OWLNamedIndividual medicationrecommendation_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_medicationrecommendation));
		OWLClassAssertionAxiom medicationrecommendation_classaxiom = factory
				.getOWLClassAssertionAxiom(medicationrecommendation_class, medicationrecommendation_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), medicationrecommendation_classaxiom);

		if (!activeIngredient.isEmpty()) {
			OWLDataProperty activeIngredient_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#activeIngredient"));
			OWLDataPropertyAssertionAxiom activeIngredient_axiom = factory.getOWLDataPropertyAssertionAxiom(
					activeIngredient_dataproperty, medicationrecommendation_individual, activeIngredient);
			ontology_manager.addAxiom(manager.getActiveOntology(), activeIngredient_axiom);
		}
		if (!MedicationRecommendationDescription.isEmpty()) {
			OWLDataProperty MedicationRecommendationDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#medicationRecommendationDescription"));
			OWLDataPropertyAssertionAxiom MedicationRecommendationDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(MedicationRecommendationDescription_dataproperty,
							medicationrecommendation_individual, MedicationRecommendationDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), MedicationRecommendationDescription_axiom);
		}
		if (!MedicationRecommendationName.isEmpty()) {
			OWLDataProperty MedicationRecommendationName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#medicationRecommendationName"));
			OWLDataPropertyAssertionAxiom MedicationRecommendationName_axiom = factory.getOWLDataPropertyAssertionAxiom(
					MedicationRecommendationName_dataproperty, medicationrecommendation_individual,
					MedicationRecommendationName);
			ontology_manager.addAxiom(manager.getActiveOntology(), MedicationRecommendationName_axiom);
		}
		if (!dosage.isEmpty()) {
			OWLDataProperty dosage_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dosage"));
			OWLDataPropertyAssertionAxiom dosage_axiom = factory.getOWLDataPropertyAssertionAxiom(dosage_dataproperty,
					medicationrecommendation_individual, dosage);
			ontology_manager.addAxiom(manager.getActiveOntology(), dosage_axiom);
		}
		if (!pharmaceuticalForm.isEmpty()) {
			OWLDataProperty pharmaceuticalForm_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#pharmaceuticalForm"));
			OWLDataPropertyAssertionAxiom pharmaceuticalForm_axiom = factory.getOWLDataPropertyAssertionAxiom(
					pharmaceuticalForm_dataproperty, medicationrecommendation_individual, pharmaceuticalForm);
			ontology_manager.addAxiom(manager.getActiveOntology(), pharmaceuticalForm_axiom);
		}
		if (!posology.isEmpty()) {
			OWLDataProperty posology_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#posology"));
			OWLDataPropertyAssertionAxiom posology_axiom = factory.getOWLDataPropertyAssertionAxiom(
					posology_dataproperty, medicationrecommendation_individual, posology);
			ontology_manager.addAxiom(manager.getActiveOntology(), posology_axiom);
		}

		JOptionPane.showMessageDialog(null,
				"Medication Recommendation_ID: " + name_medicationrecommendation + " was successfully created.",
				"Medication Recommendation created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println(
				"Medication Recommendation created with success.\nOption_ID: " + name_medicationrecommendation);
	}

	private void CreateFormula(String mathematicalExpression, String hasResultParameter,
			ListModel hasVariableParameter) {
		// Formula
		String name_formula = "Formula" + Integer.toString(getlastindividualnumber(Formula));
		OWLClass formula_class = factory.getOWLClass(Formula);
		OWLNamedIndividual formula_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_formula));
		OWLClassAssertionAxiom formula_classaxiom = factory.getOWLClassAssertionAxiom(formula_class,
				formula_individual);
		ontology_manager.addAxiom(manager.getActiveOntology(), formula_classaxiom);

		if (!mathematicalExpression.isEmpty()) {
			OWLDataProperty mathematicalExpression_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#mathematicalExpression"));
			OWLDataPropertyAssertionAxiom mathematicalExpression_axiom = factory.getOWLDataPropertyAssertionAxiom(
					mathematicalExpression_dataproperty, formula_individual, mathematicalExpression);
			ontology_manager.addAxiom(manager.getActiveOntology(), mathematicalExpression_axiom);
		}

		// hasResultParameter
		if (!(hasResultParameter.equals("-"))) {
			OWLNamedIndividual hasResultParameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + hasResultParameter));
			OWLObjectProperty hasResultParameter_dataproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasResultParameter"));
			OWLObjectPropertyAssertionAxiom hasResultParameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasResultParameter_dataproperty, formula_individual, hasResultParameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasResultParameter_axiom);
		}

		// hasVariableParameter
		OWLObjectProperty hasVariableParameter_dataproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasVariableParameter"));
		for (int i = 0; i < hasVariableParameter.getSize(); i++) {
			OWLNamedIndividual hasVariableParameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + hasVariableParameter.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom hasVariableParameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasVariableParameter_dataproperty, formula_individual, hasVariableParameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasVariableParameter_axiom);
		}

		JOptionPane.showMessageDialog(null, "Formula_ID: " + name_formula + " was successfully created.",
				"Formula created with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Formula created with success.\nOption_ID: " + name_formula);
	}

	private void EditCPG(String individual_name, String guidelinename, String author, String description,
			ListModel disease, ListModel intended, ListModel target, ListModel speciality, ListModel category,
			ListModel conditions, String plan) {
		// CPG
		// Remove Data Properties (maintain dateOfCreation and save
		// versionNumber)
		OWLNamedIndividual cpg_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		OWLDataProperty guidename = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#guidelineName"));
		OWLDataProperty authorship = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#authorship"));
		OWLDataProperty guidedescription = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#guidelineDescription"));
		OWLDataProperty dateoflastupdate = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dateOfLastUpdate"));
		OWLDataProperty versionnumber = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#versionNumber"));

		try {
			OWLLiteral literal = cpg_individual.getDataPropertyValues(guidename, manager.getActiveOntology()).iterator()
					.next();
			OWLDataPropertyAssertionAxiom dataproperty_axiom = factory.getOWLDataPropertyAssertionAxiom(guidename,
					cpg_individual, literal);

			ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

		} catch (Exception e) {
		}

		try {
			OWLLiteral literal = cpg_individual.getDataPropertyValues(authorship, manager.getActiveOntology())
					.iterator().next();
			OWLDataPropertyAssertionAxiom dataproperty_axiom = factory.getOWLDataPropertyAssertionAxiom(authorship,
					cpg_individual, literal);

			ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

		} catch (Exception e) {
		}

		try {
			OWLLiteral literal = cpg_individual.getDataPropertyValues(guidedescription, manager.getActiveOntology())
					.iterator().next();
			OWLDataPropertyAssertionAxiom dataproperty_axiom = factory
					.getOWLDataPropertyAssertionAxiom(guidedescription, cpg_individual, literal);

			ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

		} catch (Exception e) {
		}

		try {
			OWLLiteral literal = cpg_individual.getDataPropertyValues(dateoflastupdate, manager.getActiveOntology())
					.iterator().next();
			OWLDataPropertyAssertionAxiom dataproperty_axiom = factory
					.getOWLDataPropertyAssertionAxiom(dateoflastupdate, cpg_individual, literal);

			ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

		} catch (Exception e) {
		}

		String version_number_string = new String("1.0");
		int version_number_int = 0;
		try {
			OWLLiteral versionnumber_literal = cpg_individual
					.getDataPropertyValues(versionnumber, manager.getActiveOntology()).iterator().next();

			version_number_string = versionnumber_literal.getLiteral();
			String version_number_string_ant = new String(version_number_string);

			version_number_string = version_number_string.replaceAll("^(\\d)+(.){1}", "");
			version_number_string_ant = version_number_string_ant.replaceAll("(.){1}(\\d)+", "");
			version_number_int = Integer.parseInt(version_number_string);
			version_number_int++;

			version_number_string = version_number_string_ant + "." + Integer.toString(version_number_int);

			OWLDataPropertyAssertionAxiom dataproperty_axiom = factory.getOWLDataPropertyAssertionAxiom(versionnumber,
					cpg_individual, versionnumber_literal);

			ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

		} catch (Exception e) {
		}

		// Insert Data Properties in CPG Individual

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();

		String date = df.format(dateobj).toString() + "T" + df1.format(dateobj).toString();

		OWLDatatype dateTimeType = factory.getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
		OWLLiteral dateLeve = factory.getOWLLiteral(date, dateTimeType);

		OWLDatatype versionType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
		OWLLiteral version = factory.getOWLLiteral(version_number_string, versionType);

		// Create the actual assertion (triple), as an object property assertion
		// axiom
		if (!guidelinename.isEmpty()) {
			OWLDataPropertyAssertionAxiom guidename_assertion = factory.getOWLDataPropertyAssertionAxiom(guidename,
					cpg_individual, guidelinename);
			ontology_manager.addAxiom(manager.getActiveOntology(), guidename_assertion);
		}
		if (!author.isEmpty()) {
			OWLDataPropertyAssertionAxiom autorship_assertion = factory.getOWLDataPropertyAssertionAxiom(authorship,
					cpg_individual, author);
			ontology_manager.addAxiom(manager.getActiveOntology(), autorship_assertion);
		}
		if (!description.isEmpty()) {
			OWLDataPropertyAssertionAxiom guidedescription_assertion = factory
					.getOWLDataPropertyAssertionAxiom(guidedescription, cpg_individual, description);
			ontology_manager.addAxiom(manager.getActiveOntology(), guidedescription_assertion);
		}

		OWLDataPropertyAssertionAxiom dateoflastupdate_assertion = factory
				.getOWLDataPropertyAssertionAxiom(dateoflastupdate, cpg_individual, dateLeve);
		OWLDataPropertyAssertionAxiom versionnumber_assertion = factory.getOWLDataPropertyAssertionAxiom(versionnumber,
				cpg_individual, version);

		// Add Assertion Axiom to the OWL file
		ontology_manager.addAxiom(manager.getActiveOntology(), versionnumber_assertion);
		ontology_manager.addAxiom(manager.getActiveOntology(), dateoflastupdate_assertion);

		// CPG Scope individual
		OWLNamedIndividual scope_individual = null;

		OWLObjectProperty hasScope_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScope"));
		OWLClass scope_class = factory.getOWLClass(Scope);
		String name_scope = "Scope" + Integer.toString(getlastindividualnumber(Scope));
		try {
			// Remove Data Properties of Scope
			scope_individual = cpg_individual
					.getObjectPropertyValues(hasScope_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			removeAllDataProperties(scope_individual.getIRI().getFragment());
		} catch (Exception e) { // Scope doesn't exist -> create new one and
								// associate to CPG Individual
			if (target.getSize() + disease.getSize() + intended.getSize() + speciality.getSize()
					+ category.getSize() > 0) {

				scope_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_scope));
				OWLClassAssertionAxiom scope_classaxiom = factory.getOWLClassAssertionAxiom(scope_class,
						scope_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), scope_classaxiom);

				OWLObjectPropertyAssertionAxiom hasScope_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasScope_objectproperty, cpg_individual, scope_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasScope_objectpropertyaxiom);
			}
		}

		// Remove Object Properties of Scope (maintain ConditionSet)
		OWLObjectProperty hasclinicalspecialty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasClinicalSpecialty"));
		try {
			Set<OWLIndividual> clinicalspeciality_set = scope_individual.getObjectPropertyValues(hasclinicalspecialty,
					manager.getActiveOntology());

			for (OWLIndividual a : clinicalspeciality_set) {
				OWLObjectPropertyAssertionAxiom objectproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalspecialty, scope_individual, a);
				ontology_manager.removeAxiom(manager.getActiveOntology(), objectproperty_axiom);
			}
		} catch (Exception e) {
		}
		OWLObjectProperty hasguidelinecategory = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasGuidelineCategory"));
		try {
			Set<OWLIndividual> guidelinecategory_set = scope_individual.getObjectPropertyValues(hasguidelinecategory,
					manager.getActiveOntology());

			for (OWLIndividual a : guidelinecategory_set) {
				OWLObjectPropertyAssertionAxiom objectproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasguidelinecategory, scope_individual, a);
				ontology_manager.removeAxiom(manager.getActiveOntology(), objectproperty_axiom);
			}
		} catch (Exception e) {
		}

		// Handle Removing ConditionSet Data
		OWLObjectProperty hasScopeConditionSet_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasScopeConditionSet"));

		OWLNamedIndividual conditionset_individual = null;

		OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
		String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));
		try {
			// Remove Data and Object Properties of Scope (ConditionSet)

			conditionset_individual = scope_individual
					.getObjectPropertyValues(hasScopeConditionSet_objectproperty, manager.getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			removeAllDataProperties(conditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(conditionset_individual.getIRI().getFragment());

			if (conditions.getSize() == 0) {
				OWLObjectPropertyAssertionAxiom hasScopeConditionSet_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasScopeConditionSet_objectproperty, scope_individual,
								conditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), hasScopeConditionSet_objectpropertyaxiom);

				OWLClassAssertionAxiom conditionset_classaxiom = factory.getOWLClassAssertionAxiom(conditionset_class,
						conditionset_individual);

				ontology_manager.removeAxiom(manager.getActiveOntology(), conditionset_classaxiom);
			}

		} catch (Exception e) {
			if (conditions.getSize() > 0) {
				conditionset_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));
				OWLClassAssertionAxiom conditionset_classaxiom = factory.getOWLClassAssertionAxiom(conditionset_class,
						conditionset_individual);

				ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_classaxiom);

				OWLObjectPropertyAssertionAxiom hasScopeConditionSet_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasScopeConditionSet_objectproperty, scope_individual,
								conditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasScopeConditionSet_objectpropertyaxiom);
			}

		}

		// Remove Scope if no data
		if (target.getSize() + disease.getSize() + intended.getSize() + speciality.getSize() + category.getSize() == 0
				&& scope_individual != null) {

			OWLObjectPropertyAssertionAxiom scope_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(hasScope_objectproperty, cpg_individual, scope_individual);
			ontology_manager.removeAxiom(manager.getActiveOntology(), scope_objectpropertyaxiom);

			OWLClassAssertionAxiom scope_classaxiom = factory.getOWLClassAssertionAxiom(scope_class, scope_individual);
			ontology_manager.removeAxiom(manager.getActiveOntology(), scope_classaxiom);
		}

		// Add Data Property to Scope
		else if (target.getSize() + disease.getSize() + intended.getSize() + speciality.getSize()
				+ category.getSize() > 0) {
			OWLDataProperty disease_condition = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#diseaseOrCondition"));
			OWLDataProperty intended_user = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#intendedUser"));
			OWLDataProperty target_population = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#targetPopulation"));

			for (int i = 0; i < disease.getSize(); i++) {
				OWLDataPropertyAssertionAxiom disease_condition_assertion = factory.getOWLDataPropertyAssertionAxiom(
						disease_condition, scope_individual, (String) disease.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), disease_condition_assertion);
			}

			for (int i = 0; i < intended.getSize(); i++) {
				OWLDataPropertyAssertionAxiom intended_user_assertion = factory.getOWLDataPropertyAssertionAxiom(
						intended_user, scope_individual, (String) intended.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), intended_user_assertion);
			}

			for (int i = 0; i < target.getSize(); i++) {
				OWLDataPropertyAssertionAxiom target_population_assertion = factory.getOWLDataPropertyAssertionAxiom(
						target_population, scope_individual, (String) target.getElementAt(i));
				ontology_manager.addAxiom(manager.getActiveOntology(), target_population_assertion);
			}

			// Add Object Property to Scope (Clinical Speciality)
			for (int i = 0; i < speciality.getSize(); i++) {
				OWLNamedIndividual speciality_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + speciality.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom clinical_speciality_assertion = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalspecialty, scope_individual,
								speciality_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), clinical_speciality_assertion);
			}

			// Add Object Property to Scope (Guideline Category)
			for (int i = 0; i < category.getSize(); i++) {
				OWLNamedIndividual category_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + category.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom category_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasguidelinecategory, scope_individual, category_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), category_assertion);
			}

			// Add Data and Object Properties of Scope (ConditionSet)

			if (conditions.getSize() > 0) {
				OWLObjectProperty hasCondition = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));
				for (int i = 0; i < conditions.getSize(); i++) {
					OWLNamedIndividual condition_individual = factory
							.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + conditions.getElementAt(i)));
					OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
							hasCondition, conditionset_individual, condition_individual);
					ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
				}
				OWLDataProperty conditionsetcounter_dataproperty = factory
						.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));

				OWLDatatype conditionsetcounterType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
				OWLLiteral conditionsetcounter_literal = factory.getOWLLiteral(Integer.toString(conditions.getSize()),
						conditionsetcounterType);

				OWLDataPropertyAssertionAxiom conditionsetcounter_datapropertyaxiom = factory
						.getOWLDataPropertyAssertionAxiom(conditionsetcounter_dataproperty, conditionset_individual,
								conditionsetcounter_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_datapropertyaxiom);
			}
		}

		// Remove ObjectProperty HasPlan
		OWLObjectProperty hasPlan_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPlan"));
		try {
			OWLNamedIndividual plan_individual = cpg_individual
					.getObjectPropertyValues(hasPlan_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			OWLObjectPropertyAssertionAxiom objectproperty_axiom = factory
					.getOWLObjectPropertyAssertionAxiom(hasPlan_objectproperty, cpg_individual, plan_individual);

			ontology_manager.removeAxiom(manager.getActiveOntology(), objectproperty_axiom);

		} catch (Exception e) {
		}
		// Add Plan
		if (!(plan.equals("-"))) {
			OWLNamedIndividual plan_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + plan));
			OWLObjectProperty hasPlan = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPlan"));
			OWLObjectPropertyAssertionAxiom cpg_plan = factory.getOWLObjectPropertyAssertionAxiom(hasPlan,
					cpg_individual, plan_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), cpg_plan);
		}

		JOptionPane.showMessageDialog(null, "CPG_ID: " + individual_name + " was successfully edited.",
				"Clinical Practice Guideline edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Clinical Practice Guideline was successfully edited.\nCPG_ID: " + individual_name);

	}

	private void EditPlan(String individual_name, String description, EditPlanPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions, String stop_clinicaltask, ListModel preconditions,
			ListModel outcomeconditions, ListModel triggerconditions, ListModel firsttasks,
			EditPlanPage10 duration_restrictions, String sync_clinicaltask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {
		// Plan
		OWLNamedIndividual plan_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		OWLNamedIndividual periodicity_individual = null, outcome_individual = null, precondition_individual = null,
				triggercondition_individual = null, duration_individual = null;

		OWLObjectProperty periodicity_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
		OWLObjectProperty outcome_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty duration_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));

		String name_periodicity = "Periodicity" + Integer.toString(getlastindividualnumber(Periodicity));
		OWLClass periodicity_class = factory.getOWLClass(Periodicity);

		try {
			periodicity_individual = plan_individual
					.getObjectPropertyValues(periodicity_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			if ((periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
					&& periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
					&& periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {

				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remove Periodicity
				try { // Remove PeriodicityConditionSet in case it exists
					OWLClass periodicityconditionset_class = factory.getOWLClass(ConditionSet);

					OWLObjectProperty periodicityconditionset_objectproperty = factory
							.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));

					OWLNamedIndividual periodicityconditionset_individual = periodicity_individual
							.getObjectPropertyValues(periodicityconditionset_objectproperty,
									manager.getActiveOntology())
							.iterator().next().asOWLNamedIndividual();

					// Remover Assertion entre Periodicity e
					// PeriodicityConditionSet
					OWLObjectPropertyAssertionAxiom periodicityconditionset_axiom = factory
							.getOWLObjectPropertyAssertionAxiom(periodicityconditionset_objectproperty,
									periodicity_individual, periodicityconditionset_individual);
					ontology_manager.removeAxiom(manager.getActiveOntology(), periodicityconditionset_axiom);

					// Remover Individual PeriodicityConditionSet
					periodicityconditionset_individual.accept(remover);
				} catch (Exception e) {
				}

				// Remover Assertion entre Plan e Periodicity
				OWLObjectPropertyAssertionAxiom periodicity_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						periodicity_objectproperty, plan_individual, periodicity_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), periodicity_axiom);

				// Remover Individual Periodicity
				periodicity_individual.accept(remover);
			}

		} catch (Exception e) { // if doesn't exist -> create
			if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
					&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
					|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {
				periodicity_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_periodicity));
				OWLClassAssertionAxiom periodicity_classaxiom = factory.getOWLClassAssertionAxiom(periodicity_class,
						periodicity_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_classaxiom);

				OWLObjectPropertyAssertionAxiom periodicity_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(periodicity_objectproperty, plan_individual,
								periodicity_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_objectpropertyaxiom);
			}
		}

		String name_outcome = "O" + Integer.toString(getlastindividualnumber(Outcome));
		OWLClass outcome_class = factory.getOWLClass(Outcome);

		try {
			outcome_individual = plan_individual
					.getObjectPropertyValues(outcome_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			if (outcomeconditions.getSize() == 0) {
				// Remover Outcome
				OWLClass outcomeconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty outcomeconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcomeConditionSet"));

				OWLNamedIndividual outcomeconditionset_individual = outcome_individual
						.getObjectPropertyValues(outcomeconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre Plan e OutcomeCondition
				OWLObjectPropertyAssertionAxiom outcomecondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						outcome_objectproperty, plan_individual, outcome_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomecondition_axiom);

				// Remover Assertion entre OutcomeCondition e
				// OutcomeConditionSet
				OWLObjectPropertyAssertionAxiom outcomeconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						outcomeconditionset_objectproperty, outcome_individual, outcomeconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomeconditionset_axiom);

				// Remover Individual OutcomeConditionSet
				OWLClassAssertionAxiom outcomeconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(outcomeconditionset_class, outcomeconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomeconditionset_classaxiom);

				// Remover Individual OutcomeCondition
				OWLClassAssertionAxiom outcome_classaxiom = factory.getOWLClassAssertionAxiom(outcome_class,
						outcome_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcome_classaxiom);
			}

		} catch (Exception e) { // if doesn't exist -> create
			if (outcomeconditions.getSize() > 0) {
				outcome_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_outcome));
				OWLClassAssertionAxiom outcome_classaxiom = factory.getOWLClassAssertionAxiom(outcome_class,
						outcome_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), outcome_classaxiom);

				OWLObjectPropertyAssertionAxiom outcome_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(outcome_objectproperty, plan_individual,
								outcome_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), outcome_objectpropertyaxiom);
			}
		}

		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		OWLClass precondition_class = factory.getOWLClass(Precondition);

		try {
			precondition_individual = plan_individual
					.getObjectPropertyValues(precondition_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			if (preconditions.getSize() == 0) {
				// Remover PreCondition
				OWLClass preconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty preconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));

				OWLNamedIndividual preconditionset_individual = precondition_individual
						.getObjectPropertyValues(preconditionset_objectproperty, manager.getActiveOntology()).iterator()
						.next().asOWLNamedIndividual();

				// Remover Assertion entre Plan e PreCondition
				OWLObjectPropertyAssertionAxiom precondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						precondition_objectproperty, plan_individual, precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_axiom);

				// Remover Assertion entre PreCondition e
				// PreConditionSet
				OWLObjectPropertyAssertionAxiom preconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						preconditionset_objectproperty, precondition_individual, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_axiom);

				// Remover Individual PreConditionSet
				OWLClassAssertionAxiom preconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(preconditionset_class, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_classaxiom);

				// Remover Individual PreCondition
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_classaxiom);
			}

		} catch (Exception e) {
			if (preconditions.getSize() > 0) {
				precondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxiom);

				OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, plan_individual,
								precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			}
		}

		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);

		try {
			triggercondition_individual = plan_individual
					.getObjectPropertyValues(triggercondition_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			if (triggerconditions.getSize() == 0) {
				// Remover TriggerCondition
				OWLClass triggerconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty triggerconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

				OWLNamedIndividual triggerconditionset_individual = triggercondition_individual
						.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre Plan e TriggerCondition
				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, plan_individual, triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggercondition_axiom);

				// Remover Assertion entre TriggerCondition e
				// TriggerConditionSet
				OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggerconditionset_objectproperty, triggercondition_individual,
						triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_axiom);

				// Remover Individual TriggerConditionSet
				OWLClassAssertionAxiom triggerconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(triggerconditionset_class, triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_classaxiom);

				// Remover Individual TriggerCondition
				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(triggercondition_class,
						triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), trigger_classaxiom);
			}
		} catch (Exception e) {
			if (triggerconditions.getSize() > 0) {
				triggercondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));
				OWLClassAssertionAxiom triggercondition_classaxiom = factory
						.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxiom);

				OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, plan_individual,
								triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			}
		}

		String name_duration = "Duration" + Integer.toString(getlastindividualnumber(Duration));
		OWLClass duration_class = factory.getOWLClass(Duration);

		try {
			duration_individual = plan_individual
					.getObjectPropertyValues(duration_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			if ((duration_restrictions.maxduration_textfield.getText().isEmpty()
					&& duration_restrictions.minduration_textfield.getText().isEmpty())
					&& duration_restrictions.duration_textfield.getText().isEmpty()) {
				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remover Assertion entre Plan e Duration
				OWLObjectPropertyAssertionAxiom duration_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						duration_objectproperty, plan_individual, duration_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), duration_axiom);

				// Remove Duration Individual
				duration_individual.accept(remover);
			}
		} catch (Exception e) {
			if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
					&& !duration_restrictions.minduration_textfield.getText().isEmpty())
					|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
				duration_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_duration));
				OWLClassAssertionAxiom duration_classaxiom = factory.getOWLClassAssertionAxiom(duration_class,
						duration_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), duration_classaxiom);

				OWLObjectPropertyAssertionAxiom duration_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(duration_objectproperty, plan_individual,
								duration_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), duration_objectpropertyaxiom);
			}
		}

		// DataProperty Generaldescription
		generalDescription(plan_individual, description);

		// Handle periodicity
		if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
				&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
				|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {
			periodicity(periodicity_individual, periodicity_restrictions, periodicity_stop_restrictions);
		}

		// stopConditionTask
		stopconditiontask(plan_individual, stop_clinicaltask);

		// syncTask
		synctask(plan_individual, sync_clinicaltask);

		// hasOutcome
		if (outcomeconditions.getSize() > 0) {
			outcomecondition(outcome_individual, outcomeconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			precondition(precondition_individual, preconditions);
		}

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// HasFirstTask
		firsttask(plan_individual, firsttasks);

		// Duration
		if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
				&& !duration_restrictions.minduration_textfield.getText().isEmpty())
				|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
			duration(duration_individual, duration_restrictions);
		}

		// Nexttask
		nexttask(plan_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		// Update CPG DateofLastUpdate + Version associated with this Plan
		updateCPG(individual_name);

		JOptionPane.showMessageDialog(null, "Plan_ID: " + individual_name + " was successfully edited.",
				"Plan edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Plan edited with success.\nPlan_ID: " + individual_name);

	}

	private void EditAction(String individual_name, String description, EditActionPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions, String stop_clinicaltask, ListModel preconditions,
			ListModel outcomeconditions, ListModel triggerconditions, int choice, ListModel exam, ListModel formula,
			ListModel procedure, ListModel medicalrecommendation, ListModel nonmedicalrecommendation,
			EditActionPage10 duration_restrictions, String sync_clinicaltask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {

		// Action
		OWLNamedIndividual action_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		OWLNamedIndividual periodicity_individual = null, outcome_individual = null, precondition_individual = null,
				triggercondition_individual = null, duration_individual = null;

		OWLObjectProperty periodicity_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPeriodicity"));
		OWLObjectProperty outcome_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcome"));
		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty duration_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasDuration"));

		String name_periodicity = "Periodicity" + Integer.toString(getlastindividualnumber(Periodicity));
		OWLClass periodicity_class = factory.getOWLClass(Periodicity);

		try {
			periodicity_individual = action_individual
					.getObjectPropertyValues(periodicity_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			if ((periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
					&& periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
					&& periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {

				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remove Periodicity
				try { // Remove PeriodicityConditionSet in case it exists
					OWLClass periodicityconditionset_class = factory.getOWLClass(ConditionSet);

					OWLObjectProperty periodicityconditionset_objectproperty = factory
							.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));

					OWLNamedIndividual periodicityconditionset_individual = periodicity_individual
							.getObjectPropertyValues(periodicityconditionset_objectproperty,
									manager.getActiveOntology())
							.iterator().next().asOWLNamedIndividual();

					// Remover Assertion entre Periodicity e
					// PeriodicityConditionSet
					OWLObjectPropertyAssertionAxiom periodicityconditionset_axiom = factory
							.getOWLObjectPropertyAssertionAxiom(periodicityconditionset_objectproperty,
									periodicity_individual, periodicityconditionset_individual);
					ontology_manager.removeAxiom(manager.getActiveOntology(), periodicityconditionset_axiom);

					// Remover Individual PeriodicityConditionSet
					periodicityconditionset_individual.accept(remover);
				} catch (Exception e) {
				}

				// Remover Assertion entre Action e Periodicity
				OWLObjectPropertyAssertionAxiom periodicity_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						periodicity_objectproperty, action_individual, periodicity_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), periodicity_axiom);

				// Remover Individual Periodicity
				periodicity_individual.accept(remover);
			}

		} catch (Exception e) { // if doesn't exist -> create
			if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
					&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
					|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {
				periodicity_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_periodicity));
				OWLClassAssertionAxiom periodicity_classaxiom = factory.getOWLClassAssertionAxiom(periodicity_class,
						periodicity_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_classaxiom);

				OWLObjectPropertyAssertionAxiom periodicity_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(periodicity_objectproperty, action_individual,
								periodicity_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), periodicity_objectpropertyaxiom);
			}
		}

		String name_outcome = "O" + Integer.toString(getlastindividualnumber(Outcome));
		OWLClass outcome_class = factory.getOWLClass(Outcome);

		try {
			outcome_individual = action_individual
					.getObjectPropertyValues(outcome_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			if (outcomeconditions.getSize() == 0) {
				// Remover Outcome
				OWLClass outcomeconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty outcomeconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcomeConditionSet"));

				OWLNamedIndividual outcomeconditionset_individual = outcome_individual
						.getObjectPropertyValues(outcomeconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre Action e OutcomeCondition
				OWLObjectPropertyAssertionAxiom outcomecondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						outcome_objectproperty, action_individual, outcome_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomecondition_axiom);

				// Remover Assertion entre OutcomeCondition e
				// OutcomeConditionSet
				OWLObjectPropertyAssertionAxiom outcomeconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						outcomeconditionset_objectproperty, outcome_individual, outcomeconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomeconditionset_axiom);

				// Remover Individual OutcomeConditionSet
				OWLClassAssertionAxiom outcomeconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(outcomeconditionset_class, outcomeconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcomeconditionset_classaxiom);

				// Remover Individual OutcomeCondition
				OWLClassAssertionAxiom outcome_classaxiom = factory.getOWLClassAssertionAxiom(outcome_class,
						outcome_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), outcome_classaxiom);
			}

		} catch (Exception e) { // if doesn't exist -> create
			if (outcomeconditions.getSize() > 0) {
				outcome_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_outcome));
				OWLClassAssertionAxiom outcome_classaxiom = factory.getOWLClassAssertionAxiom(outcome_class,
						outcome_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), outcome_classaxiom);

				OWLObjectPropertyAssertionAxiom outcome_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(outcome_objectproperty, action_individual,
								outcome_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), outcome_objectpropertyaxiom);
			}
		}

		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		OWLClass precondition_class = factory.getOWLClass(Precondition);

		try {
			precondition_individual = action_individual
					.getObjectPropertyValues(precondition_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			if (preconditions.getSize() == 0) {
				// Remover PreCondition
				OWLClass preconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty preconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));

				OWLNamedIndividual preconditionset_individual = precondition_individual
						.getObjectPropertyValues(preconditionset_objectproperty, manager.getActiveOntology()).iterator()
						.next().asOWLNamedIndividual();

				// Remover Assertion entre Action e PreCondition
				OWLObjectPropertyAssertionAxiom precondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						precondition_objectproperty, action_individual, precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_axiom);

				// Remover Assertion entre PreCondition e
				// PreConditionSet
				OWLObjectPropertyAssertionAxiom preconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						preconditionset_objectproperty, precondition_individual, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_axiom);

				// Remover Individual PreConditionSet
				OWLClassAssertionAxiom preconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(preconditionset_class, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_classaxiom);

				// Remover Individual PreCondition
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_classaxiom);
			}

		} catch (Exception e) {
			if (preconditions.getSize() > 0) {
				precondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxiom);

				OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, action_individual,
								precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			}
		}

		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		OWLClass triggercondition_class = factory.getOWLClass(TriggerCondition);

		try {
			triggercondition_individual = action_individual
					.getObjectPropertyValues(triggercondition_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			if (triggerconditions.getSize() == 0) {
				// Remover TriggerCondition
				OWLClass triggerconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty triggerconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

				OWLNamedIndividual triggerconditionset_individual = triggercondition_individual
						.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre Action e TriggerCondition
				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, action_individual, triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggercondition_axiom);

				// Remover Assertion entre TriggerCondition e
				// TriggerConditionSet
				OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggerconditionset_objectproperty, triggercondition_individual,
						triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_axiom);

				// Remover Individual TriggerConditionSet
				OWLClassAssertionAxiom triggerconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(triggerconditionset_class, triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_classaxiom);

				// Remover Individual TriggerCondition
				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(triggercondition_class,
						triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), trigger_classaxiom);
			}
		} catch (Exception e) {
			if (triggerconditions.getSize() > 0) {
				triggercondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));
				OWLClassAssertionAxiom triggercondition_classaxiom = factory
						.getOWLClassAssertionAxiom(triggercondition_class, triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_classaxiom);

				OWLObjectPropertyAssertionAxiom triggercondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(triggercondition_objectproperty, action_individual,
								triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_objectpropertyaxiom);
			}
		}

		String name_duration = "Duration" + Integer.toString(getlastindividualnumber(Duration));
		OWLClass duration_class = factory.getOWLClass(Duration);

		try {
			duration_individual = action_individual
					.getObjectPropertyValues(duration_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();

			if ((duration_restrictions.maxduration_textfield.getText().isEmpty()
					&& duration_restrictions.minduration_textfield.getText().isEmpty())
					&& duration_restrictions.duration_textfield.getText().isEmpty()) {

				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remover Assertion entre Action e Duration
				OWLObjectPropertyAssertionAxiom duration_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						duration_objectproperty, action_individual, duration_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), duration_axiom);

				// Remove Duration Individual
				duration_individual.accept(remover);

			}
		} catch (Exception e) {
			if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
					&& !duration_restrictions.minduration_textfield.getText().isEmpty())
					|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
				duration_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_duration));
				OWLClassAssertionAxiom duration_classaxiom = factory.getOWLClassAssertionAxiom(duration_class,
						duration_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), duration_classaxiom);

				OWLObjectPropertyAssertionAxiom duration_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(duration_objectproperty, action_individual,
								duration_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), duration_objectpropertyaxiom);
			}
		}

		// DataProperty Generaldescription
		generalDescription(action_individual, description);

		// Handle periodicity
		if ((!periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty()
				&& !periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())
				|| !periodicity_restrictions.periodicity_textfield.getText().isEmpty()) {
			periodicity(periodicity_individual, periodicity_restrictions, periodicity_stop_restrictions);
		}

		// stopConditionTask
		stopconditiontask(action_individual, stop_clinicaltask);

		// syncTask
		synctask(action_individual, sync_clinicaltask);

		// hasOutcome
		if (outcomeconditions.getSize() > 0) {
			outcomecondition(outcome_individual, outcomeconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			precondition(precondition_individual, preconditions);
		}

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// ClinicalActionType
		clinicalactiontype(action_individual, choice, exam, formula, procedure, medicalrecommendation,
				nonmedicalrecommendation);

		// Duration
		if ((!duration_restrictions.maxduration_textfield.getText().isEmpty()
				&& !duration_restrictions.minduration_textfield.getText().isEmpty())
				|| !duration_restrictions.duration_textfield.getText().isEmpty()) {
			duration(duration_individual, duration_restrictions);
		}

		// Nexttask
		nexttask(action_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Action_ID: " + individual_name + " was successfully edited.",
				"Action edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Action edited with success.\nAction_ID: " + individual_name);

	}

	private void EditQuestion(String individual_name, String description, ListModel preconditions,
			ListModel triggerconditions, ListModel parameters, String synctask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {
		// Question
		OWLNamedIndividual question_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty parameter_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasParameter"));

		OWLNamedIndividual precondition_individual = null, triggercondition_individual = null;

		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		OWLClass precondition_class = factory.getOWLClass(Precondition);
		try {
			precondition_individual = question_individual
					.getObjectPropertyValues(precondition_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			if (preconditions.getSize() == 0) {
				// Remover PreCondition
				OWLClass preconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty preconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));

				OWLNamedIndividual preconditionset_individual = precondition_individual
						.getObjectPropertyValues(preconditionset_objectproperty, manager.getActiveOntology()).iterator()
						.next().asOWLNamedIndividual();

				// Remover Assertion entre Question e PreCondition
				OWLObjectPropertyAssertionAxiom precondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						precondition_objectproperty, question_individual, precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_axiom);

				// Remover Assertion entre PreCondition e
				// PreConditionSet
				OWLObjectPropertyAssertionAxiom preconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						preconditionset_objectproperty, precondition_individual, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_axiom);

				// Remover Individual PreConditionSet
				OWLClassAssertionAxiom preconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(preconditionset_class, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_classaxiom);

				// Remover Individual PreCondition
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_classaxiom);
			}

		} catch (Exception e) {
			if (preconditions.getSize() > 0) {
				precondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxiom);

				OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, question_individual,
								precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			}
		}

		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		OWLClass trigger_class = factory.getOWLClass(TriggerCondition);

		try {
			triggercondition_individual = question_individual
					.getObjectPropertyValues(triggercondition_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			if (triggerconditions.getSize() == 0) {
				// Remover TriggerCondition
				OWLClass triggerconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty triggerconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

				OWLNamedIndividual triggerconditionset_individual = triggercondition_individual
						.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre End e TriggerCondition
				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, question_individual, triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggercondition_axiom);

				// Remover Assertion entre TriggerCondition e
				// TriggerConditionSet
				OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggerconditionset_objectproperty, triggercondition_individual,
						triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_axiom);

				// Remover Individual TriggerConditionSet
				OWLClassAssertionAxiom triggerconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(triggerconditionset_class, triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_classaxiom);

				// Remover Individual TriggerCondition
				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), trigger_classaxiom);
			}

		} catch (Exception e) {
			if (triggerconditions.getSize() > 0) {
				// TriggerCondition Constructor
				triggercondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), trigger_classaxiom);

				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, question_individual, triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_axiom);
			}
		}

		// DataProperty generalDescription
		generalDescription(question_individual, description);

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			precondition(precondition_individual, preconditions);
		}

		// SyncTask
		synctask(question_individual, synctask);

		try { // Remove Parameters if exists
			Set<OWLIndividual> hasParameter_individuals = question_individual
					.getObjectPropertyValues(parameter_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : hasParameter_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(parameter_objectproperty, question_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}
		// Parameter
		for (int i = 0; i < parameters.getSize(); i++) {
			OWLNamedIndividual parameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + parameters.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom parameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					parameter_objectproperty, question_individual, parameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameter_axiom);
		}

		// Next task
		nexttask(question_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Question_ID: " + individual_name + " was successfully edited.",
				"Question edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Question edited with success.\nQuestion_ID: " + individual_name);
	}

	private void EditDecision(String individual_name, String description, ListModel preconditions,
			ListModel triggerconditions, ListModel options, String synctask, int nexttype, ListModel alternativetask,
			String next, ListModel parallel, ListModel preferencealternative) {
		// Decision
		OWLNamedIndividual decision_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		OWLObjectProperty precondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreCondition"));
		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));
		OWLObjectProperty option_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOption"));

		OWLNamedIndividual precondition_individual = null, triggercondition_individual = null;

		String name_precondition = "PC" + Integer.toString(getlastindividualnumber(Precondition));
		OWLClass precondition_class = factory.getOWLClass(Precondition);

		try {
			precondition_individual = decision_individual
					.getObjectPropertyValues(precondition_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			if (preconditions.getSize() == 0) {
				// Remover PreCondition
				OWLClass preconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty preconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));

				OWLNamedIndividual preconditionset_individual = precondition_individual
						.getObjectPropertyValues(preconditionset_objectproperty, manager.getActiveOntology()).iterator()
						.next().asOWLNamedIndividual();

				// Remover Assertion entre Decision e PreCondition
				OWLObjectPropertyAssertionAxiom precondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						precondition_objectproperty, decision_individual, precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_axiom);

				// Remover Assertion entre PreCondition e
				// PreConditionSet
				OWLObjectPropertyAssertionAxiom preconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						preconditionset_objectproperty, precondition_individual, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_axiom);

				// Remover Individual PreConditionSet
				OWLClassAssertionAxiom preconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(preconditionset_class, preconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), preconditionset_classaxiom);

				// Remover Individual PreCondition
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), precondition_classaxiom);
			}

		} catch (Exception e) {
			if (preconditions.getSize() > 0) {
				precondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_precondition));
				OWLClassAssertionAxiom precondition_classaxiom = factory.getOWLClassAssertionAxiom(precondition_class,
						precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_classaxiom);

				OWLObjectPropertyAssertionAxiom precondition_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(precondition_objectproperty, decision_individual,
								precondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), precondition_objectpropertyaxiom);
			}
		}

		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		OWLClass trigger_class = factory.getOWLClass(TriggerCondition);

		try {
			triggercondition_individual = decision_individual
					.getObjectPropertyValues(triggercondition_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			if (triggerconditions.getSize() == 0) {
				// Remover TriggerCondition
				OWLClass triggerconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty triggerconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

				OWLNamedIndividual triggerconditionset_individual = triggercondition_individual
						.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre End e TriggerCondition
				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, decision_individual, triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggercondition_axiom);

				// Remover Assertion entre TriggerCondition e
				// TriggerConditionSet
				OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggerconditionset_objectproperty, triggercondition_individual,
						triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_axiom);

				// Remover Individual TriggerConditionSet
				OWLClassAssertionAxiom triggerconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(triggerconditionset_class, triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_classaxiom);

				// Remover Individual TriggerCondition
				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), trigger_classaxiom);
			}

		} catch (Exception e) {
			if (triggerconditions.getSize() > 0) {
				// TriggerCondition Constructor
				triggercondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), trigger_classaxiom);

				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, decision_individual, triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_axiom);
			}
		}

		// DataProperty generalDescription
		generalDescription(decision_individual, description);

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			triggercondition(triggercondition_individual, triggerconditions);
		}

		// PreCondition
		if (preconditions.getSize() > 0) {
			precondition(precondition_individual, preconditions);
		}

		// SyncTask
		synctask(decision_individual, synctask);

		// Option
		try { // Remove Options if exists
			Set<OWLIndividual> hasOption_individuals = decision_individual
					.getObjectPropertyValues(option_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : hasOption_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(option_objectproperty, decision_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}

		for (int i = 0; i < options.getSize(); i++) {
			OWLNamedIndividual option_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + options.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom option_axiom = factory
					.getOWLObjectPropertyAssertionAxiom(option_objectproperty, decision_individual, option_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), option_axiom);
		}

		// Next task
		nexttask(decision_individual, nexttype, alternativetask, next, parallel, preferencealternative);

		JOptionPane.showMessageDialog(null, "Decision_ID: " + individual_name + " was successfully edited.",
				"Decision edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Decision edited with success.\nDecision_ID: " + individual_name);
	}

	private void EditEnd(String individual_name, String description, ListModel triggerconditions) {
		// End
		OWLNamedIndividual end_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// DataProperty Generaldescription
		generalDescription(end_individual, description);

		OWLObjectProperty triggercondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerCondition"));

		OWLNamedIndividual triggercondition_individual = null;

		String name_triggercondition = "TC" + Integer.toString(getlastindividualnumber(TriggerCondition));
		OWLClass trigger_class = factory.getOWLClass(TriggerCondition);

		try {
			triggercondition_individual = end_individual
					.getObjectPropertyValues(triggercondition_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			if (triggerconditions.getSize() == 0) {
				// Remover TriggerCondition
				OWLClass triggerconditionset_class = factory.getOWLClass(ConditionSet);

				OWLObjectProperty triggerconditionset_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

				OWLNamedIndividual triggerconditionset_individual = triggercondition_individual
						.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();

				// Remover Assertion entre End e TriggerCondition
				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, end_individual, triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggercondition_axiom);

				// Remover Assertion entre TriggerCondition e
				// TriggerConditionSet
				OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggerconditionset_objectproperty, triggercondition_individual,
						triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_axiom);

				// Remover Individual TriggerConditionSet
				OWLClassAssertionAxiom triggerconditionset_classaxiom = factory
						.getOWLClassAssertionAxiom(triggerconditionset_class, triggerconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), triggerconditionset_classaxiom);

				// Remover Individual TriggerCondition
				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), trigger_classaxiom);

			}
		} catch (Exception e) {
			if (triggerconditions.getSize() > 0) {
				// TriggerCondition Constructor
				triggercondition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_triggercondition));

				OWLClassAssertionAxiom trigger_classaxiom = factory.getOWLClassAssertionAxiom(trigger_class,
						triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), trigger_classaxiom);

				OWLObjectPropertyAssertionAxiom triggercondition_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						triggercondition_objectproperty, end_individual, triggercondition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), triggercondition_axiom);
			}

		}

		// TriggerCondition
		if (triggerconditions.getSize() > 0) {
			triggercondition(triggercondition_individual, triggerconditions);
		}

		JOptionPane.showMessageDialog(null, "End_ID: " + individual_name + " was successfully edited.",
				"End edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("End edited with success.\nEnd_ID: " + individual_name);

	}

	private void EditExam(String individual_name, String examName, String examDescription) {
		// Exam
		// Remove Data Properties
		OWLNamedIndividual exam_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Insert Data Properties
		if (!examDescription.isEmpty()) {
			OWLDataProperty examDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#examDescription"));
			OWLDataPropertyAssertionAxiom examDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(examDescription_dataproperty, exam_individual, examDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), examDescription_axiom);
		}

		if (!examName.isEmpty()) {
			OWLDataProperty examName_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#examName"));
			OWLDataPropertyAssertionAxiom examName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(examName_dataproperty, exam_individual, examName);
			ontology_manager.addAxiom(manager.getActiveOntology(), examName_axiom);
		}

		JOptionPane.showMessageDialog(null, "Exam_ID: " + individual_name + " was successfully edited.",
				"Exam edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Exam edited with success.\nOption_ID: " + individual_name);
	}

	private void EditFormula(String individual_name, String mathematicalExpression, String hasResultParameter,
			ListModel hasVariableParameter) {
		// Formula
		// Remove Data and Object Properties
		OWLNamedIndividual formula_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);
		removeAllObjectProperties(individual_name);

		// Insert Data Properties
		if (!mathematicalExpression.isEmpty()) {
			OWLDataProperty mathematicalExpression_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#mathematicalExpression"));
			OWLDataPropertyAssertionAxiom mathematicalExpression_axiom = factory.getOWLDataPropertyAssertionAxiom(
					mathematicalExpression_dataproperty, formula_individual, mathematicalExpression);
			ontology_manager.addAxiom(manager.getActiveOntology(), mathematicalExpression_axiom);
		}

		// hasResultParameter
		if (!(hasResultParameter.equals("-"))) {
			OWLNamedIndividual hasResultParameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + hasResultParameter));

			OWLObjectProperty hasResultParameter_dataproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasResultParameter"));
			OWLObjectPropertyAssertionAxiom hasResultParameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasResultParameter_dataproperty, formula_individual, hasResultParameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasResultParameter_axiom);
		}

		// hasVariableParameter
		OWLObjectProperty hasVariableParameter_dataproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasVariableParameter"));
		for (int i = 0; i < hasVariableParameter.getSize(); i++) {
			OWLNamedIndividual hasVariableParameter_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + hasVariableParameter.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom hasVariableParameter_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasVariableParameter_dataproperty, formula_individual, hasVariableParameter_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasVariableParameter_axiom);
		}

		JOptionPane.showMessageDialog(null, "Formula_ID: " + individual_name + " was successfully edited.",
				"Formula edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Formula edited with success.\nOption_ID: " + individual_name);
	}

	private void EditProcedure(String individual_name, String procedureName, String procedureDescription) {
		// Procedure
		// Remove Data Properties
		OWLNamedIndividual procedure_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Insert Data Properties
		if (!procedureDescription.isEmpty()) {
			OWLDataProperty procedureDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#procedureDescription"));
			OWLDataPropertyAssertionAxiom procedureDescription_axiom = factory.getOWLDataPropertyAssertionAxiom(
					procedureDescription_dataproperty, procedure_individual, procedureDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), procedureDescription_axiom);
		}

		if (!procedureName.isEmpty()) {
			OWLDataProperty procedureName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#procedureName"));
			OWLDataPropertyAssertionAxiom procedureName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(procedureName_dataproperty, procedure_individual, procedureName);
			ontology_manager.addAxiom(manager.getActiveOntology(), procedureName_axiom);
		}

		JOptionPane.showMessageDialog(null, "Procedure_ID: " + individual_name + " was successfully edited.",
				"Procedure edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Procedure edited with success.\nProcedure_ID: " + individual_name);
	}

	private void EditMedicationRecommendation(String individual_name, String MedicationRecommendationName,
			String MedicationRecommendationDescription, String activeIngredient, String dosage,
			String pharmaceuticalForm, String posology) {
		// NonMedicationRecommendation
		// Remove Data Properties
		OWLNamedIndividual medicationrecommendation_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Insert Data Properties
		if (!activeIngredient.isEmpty()) {
			OWLDataProperty activeIngredient_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#activeIngredient"));
			OWLDataPropertyAssertionAxiom activeIngredient_axiom = factory.getOWLDataPropertyAssertionAxiom(
					activeIngredient_dataproperty, medicationrecommendation_individual, activeIngredient);
			ontology_manager.addAxiom(manager.getActiveOntology(), activeIngredient_axiom);
		}
		if (!MedicationRecommendationDescription.isEmpty()) {
			OWLDataProperty MedicationRecommendationDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#medicationRecommendationDescription"));
			OWLDataPropertyAssertionAxiom MedicationRecommendationDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(MedicationRecommendationDescription_dataproperty,
							medicationrecommendation_individual, MedicationRecommendationDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), MedicationRecommendationDescription_axiom);
		}
		if (!MedicationRecommendationName.isEmpty()) {
			OWLDataProperty MedicationRecommendationName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#medicationRecommendationName"));
			OWLDataPropertyAssertionAxiom MedicationRecommendationName_axiom = factory.getOWLDataPropertyAssertionAxiom(
					MedicationRecommendationName_dataproperty, medicationrecommendation_individual,
					MedicationRecommendationName);
			ontology_manager.addAxiom(manager.getActiveOntology(), MedicationRecommendationName_axiom);
		}
		if (!dosage.isEmpty()) {
			OWLDataProperty dosage_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dosage"));
			OWLDataPropertyAssertionAxiom dosage_axiom = factory.getOWLDataPropertyAssertionAxiom(dosage_dataproperty,
					medicationrecommendation_individual, dosage);
			ontology_manager.addAxiom(manager.getActiveOntology(), dosage_axiom);
		}
		if (!pharmaceuticalForm.isEmpty()) {
			OWLDataProperty pharmaceuticalForm_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#pharmaceuticalForm"));
			OWLDataPropertyAssertionAxiom pharmaceuticalForm_axiom = factory.getOWLDataPropertyAssertionAxiom(
					pharmaceuticalForm_dataproperty, medicationrecommendation_individual, pharmaceuticalForm);
			ontology_manager.addAxiom(manager.getActiveOntology(), pharmaceuticalForm_axiom);
		}
		if (!posology.isEmpty()) {
			OWLDataProperty posology_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#posology"));
			OWLDataPropertyAssertionAxiom posology_axiom = factory.getOWLDataPropertyAssertionAxiom(
					posology_dataproperty, medicationrecommendation_individual, posology);
			ontology_manager.addAxiom(manager.getActiveOntology(), posology_axiom);
		}

		JOptionPane.showMessageDialog(null,
				"Medication Recommendation_ID: " + individual_name + " was successfully edited.",
				"Medication Recommendation edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Medication Recommendation edited with success.\nOption_ID: " + individual_name);
	}

	private void EditNonMedicationRecommendation(String individual_name, String nonMedicationRecommendationName,
			String nonMedicationRecommendationDescription) {

		// NonMedicationRecommendation
		// Remove Data Properties
		OWLNamedIndividual nonmedicationrecommendation_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Insert Data Properties
		if (!nonMedicationRecommendationDescription.isEmpty()) {
			OWLDataProperty nonMedicationRecommendationDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#nonMedicationRecommendationDescription"));
			OWLDataPropertyAssertionAxiom nonMedicationRecommendationDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(nonMedicationRecommendationDescription_dataproperty,
							nonmedicationrecommendation_individual, nonMedicationRecommendationDescription);
			ontology_manager.addAxiom(manager.getActiveOntology(), nonMedicationRecommendationDescription_axiom);
		}

		if (!nonMedicationRecommendationName.isEmpty()) {
			OWLDataProperty nonMedicationRecommendationName_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#nonMedicationRecommendationName"));
			OWLDataPropertyAssertionAxiom nonMedicationRecommendationName_axiom = factory
					.getOWLDataPropertyAssertionAxiom(nonMedicationRecommendationName_dataproperty,
							nonmedicationrecommendation_individual, nonMedicationRecommendationName);
			ontology_manager.addAxiom(manager.getActiveOntology(), nonMedicationRecommendationName_axiom);
		}

		JOptionPane.showMessageDialog(null,
				"Non-Medication Recommendation_ID: " + individual_name + " was successfully edited.",
				"Non-Medication Recommendation edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Non-Medication Recommendation edited with success.\nOption_ID: " + individual_name);
	}

	private void EditCondition(String individual_name, int type_condition, String value, String comparisonOperator,
			String parameterIdentifier, String conditionParameter, String unit, String maxtemporalrestrictionvalue,
			String mintemporalrestrictionvalue, String temporaloperator, String temporalunit) {
		// Condition
		OWLNamedIndividual condition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Remove ComparisonOperator Axiom
		OWLObjectProperty hasComparisonOperator_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));
		OWLNamedIndividual comparisonoperator_individual = condition_individual
				.getObjectPropertyValues(hasComparisonOperator_objectproperty, manager.getActiveOntology()).iterator()
				.next().asOWLNamedIndividual();

		OWLObjectPropertyAssertionAxiom hasComparisonOperator_objectpropertyaxiom = factory
				.getOWLObjectPropertyAssertionAxiom(hasComparisonOperator_objectproperty, condition_individual,
						comparisonoperator_individual);
		ontology_manager.removeAxiom(manager.getActiveOntology(), hasComparisonOperator_objectpropertyaxiom);

		// Remove Dataproperty + Object Property of Temporal Restriction
		OWLObjectProperty hasTemporalRestriction_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

		OWLNamedIndividual temporalrestriction_individual = null;

		String name_temporalrestriction = "TR" + Integer.toString(getlastindividualnumber(TemporalRestriction));
		OWLClass temporalrestriction_class = factory.getOWLClass(TemporalRestriction);

		try {
			temporalrestriction_individual = condition_individual
					.getObjectPropertyValues(hasTemporalRestriction_objectproperty, manager.getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			removeAllDataProperties(temporalrestriction_individual.getIRI().getFragment());
			removeAllObjectProperties(temporalrestriction_individual.getIRI().getFragment());

			if (maxtemporalrestrictionvalue.isEmpty() || mintemporalrestrictionvalue.isEmpty()) {

				OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory
						.getOWLObjectPropertyAssertionAxiom(hasTemporalRestriction_objectproperty, condition_individual,
								temporalrestriction_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

				OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
						.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);
			}
		} catch (Exception e) {
			if (!maxtemporalrestrictionvalue.isEmpty() && !mintemporalrestrictionvalue.isEmpty()) {

				temporalrestriction_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_temporalrestriction));

				OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
						.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);
			}
		}

		// Recreate Condition

		// Adding DataProperty of Condition
		if (type_condition == 0) { // Condition with numericalValue

			OWLDataProperty numericalValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

			if (!(value.isEmpty())) {

				OWLDatatype numericalValue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalValue_literal = factory.getOWLLiteral(value, numericalValue_type);

				OWLDataPropertyAssertionAxiom numericalValue_assertion = factory.getOWLDataPropertyAssertionAxiom(
						numericalValue_dataproperty, condition_individual, numericalValue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalValue_assertion);
			}

		} else if (type_condition == 1) { // Condition with qualitativeValue

			OWLDataProperty qualitativeValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));
			if (!value.isEmpty()) {
				OWLDataPropertyAssertionAxiom qualitativeValue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(qualitativeValue_dataproperty, condition_individual, value);

				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativeValue_assertion);
			}
		}

		OWLDataProperty parameterIdentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty conditionParameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!parameterIdentifier.isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterIdentifier_assertion = factory.getOWLDataPropertyAssertionAxiom(
					parameterIdentifier_dataproperty, condition_individual, parameterIdentifier);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterIdentifier_assertion);
		}
		if (!conditionParameter.isEmpty()) {
			OWLDataPropertyAssertionAxiom conditionParameter_assertion = factory.getOWLDataPropertyAssertionAxiom(
					conditionParameter_dataproperty, condition_individual, conditionParameter);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionParameter_assertion);
		}
		if (!unit.isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_assertion = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					condition_individual, unit);
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_assertion);
		}

		// Linking ObjectProperty comparisonOperator of Condition
		if (!(comparisonOperator.equals("-"))) {
			OWLNamedIndividual comparisonOperator_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + comparisonOperator));
			OWLObjectProperty comparisonOperator_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));

			OWLObjectPropertyAssertionAxiom comparisonOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					comparisonOperator_objectproperty, condition_individual, comparisonOperator_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), comparisonOperator_assertion);
		}

		// Adding and Linking ObjectProperty TemporalRestriction of Condition
		if (!maxtemporalrestrictionvalue.isEmpty() && !mintemporalrestrictionvalue.isEmpty()) {

			OWLObjectProperty temporalrestriction_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

			OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					temporalrestriction_objectproperty, condition_individual, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

			// Adding DataProperty (max Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty maxtemporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxTemporalRestrictionValue"));

			OWLDatatype maxtemporalrestrictionvalue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(maxtemporalrestrictionvalue.isEmpty())) {
				OWLLiteral maxtemporalrestrictionvalue_literal = factory.getOWLLiteral(maxtemporalrestrictionvalue,
						maxtemporalrestrictionvalue_type);

				OWLDataPropertyAssertionAxiom maxtemporalrestrictionvalue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(maxtemporalrestrictionvalue_dataproperty,
								temporalrestriction_individual, maxtemporalrestrictionvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), maxtemporalrestrictionvalue_assertion);
			}

			// Adding DataProperty (min Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty mintemporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minTemporalRestrictionValue"));

			if (!(mintemporalrestrictionvalue.isEmpty())) {
				OWLLiteral mintemporalrestrictionvalue_literal = factory.getOWLLiteral(mintemporalrestrictionvalue,
						maxtemporalrestrictionvalue_type);

				OWLDataPropertyAssertionAxiom mintemporalrestrictionvalue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(mintemporalrestrictionvalue_dataproperty,
								temporalrestriction_individual, mintemporalrestrictionvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), mintemporalrestrictionvalue_assertion);
			}

			// Linking ObjectProperty TemporalOperator of TemporalRestriction
			if (!(temporaloperator.equals("-"))) {
				OWLNamedIndividual temporalOperator_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporaloperator));
				OWLObjectProperty temporalOperator_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalOperator"));

				OWLObjectPropertyAssertionAxiom temporalOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalOperator_objectproperty, temporalrestriction_individual, temporalOperator_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalOperator_assertion);
			}

			// Linking ObjectProperty TemporalUnit of TemporalRestriction
			if (!(temporalunit.equals("-"))) {
				OWLNamedIndividual temporalunit_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporalunit));
				OWLObjectProperty temporalunit_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

				OWLObjectPropertyAssertionAxiom temporalunit_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalunit_objectproperty, temporalrestriction_individual, temporalunit_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_assertion);
			}
		}

		JOptionPane.showMessageDialog(null, "Condition was edited successfully.\nCondition_ID: " + individual_name,
				"Clinical Condition edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Condition was edited successfully.\nCondition_ID: " + individual_name);

	}

	private void EditCondition(String individual_name, int type_condition, String value, String comparisonOperator,
			String parameterIdentifier, String conditionParameter, String unit, String temporalrestrictionvalue,
			String temporaloperator, String temporalunit) {
		// Condition
		OWLNamedIndividual condition_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Remove ComparisonOperator Axiom
		OWLObjectProperty hasComparisonOperator_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));
		OWLNamedIndividual comparisonoperator_individual = condition_individual
				.getObjectPropertyValues(hasComparisonOperator_objectproperty, manager.getActiveOntology()).iterator()
				.next().asOWLNamedIndividual();

		OWLObjectPropertyAssertionAxiom hasComparisonOperator_objectpropertyaxiom = factory
				.getOWLObjectPropertyAssertionAxiom(hasComparisonOperator_objectproperty, condition_individual,
						comparisonoperator_individual);
		ontology_manager.removeAxiom(manager.getActiveOntology(), hasComparisonOperator_objectpropertyaxiom);

		// Remove Dataproperty + Object Property of Temporal Restriction
		OWLObjectProperty hasTemporalRestriction_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

		OWLNamedIndividual temporalrestriction_individual = null;

		String name_temporalrestriction = "TR" + Integer.toString(getlastindividualnumber(TemporalRestriction));
		OWLClass temporalrestriction_class = factory.getOWLClass(TemporalRestriction);
		try {
			temporalrestriction_individual = condition_individual
					.getObjectPropertyValues(hasTemporalRestriction_objectproperty, manager.getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			removeAllDataProperties(temporalrestriction_individual.getIRI().getFragment());
			removeAllObjectProperties(temporalrestriction_individual.getIRI().getFragment());

			if (temporalrestrictionvalue.isEmpty()) {
				OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory
						.getOWLObjectPropertyAssertionAxiom(hasTemporalRestriction_objectproperty, condition_individual,
								temporalrestriction_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

				OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
						.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);

			}
		} catch (Exception e) {
			if (!temporalrestrictionvalue.isEmpty()) {
				temporalrestriction_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_temporalrestriction));
				OWLClassAssertionAxiom temporalrestriction_class_assertion = factory
						.getOWLClassAssertionAxiom(temporalrestriction_class, temporalrestriction_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_class_assertion);
			}
		}

		// Recreate Condition

		// Adding DataProperty of Condition
		if (type_condition == 0) { // Condition with numericalValue

			OWLDataProperty numericalValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

			if (!(value.isEmpty())) {
				OWLDatatype numericalValue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalValue_literal = factory.getOWLLiteral(value, numericalValue_type);

				OWLDataPropertyAssertionAxiom numericalValue_assertion = factory.getOWLDataPropertyAssertionAxiom(
						numericalValue_dataproperty, condition_individual, numericalValue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalValue_assertion);
			}

		} else if (type_condition == 1) { // Condition with qualitativeValue

			OWLDataProperty qualitativeValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));

			if (!value.isEmpty()) {
				OWLDataPropertyAssertionAxiom qualitativeValue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(qualitativeValue_dataproperty, condition_individual, value);

				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativeValue_assertion);
			}
		}

		OWLDataProperty parameterIdentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty conditionParameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!parameterIdentifier.isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterIdentifier_assertion = factory.getOWLDataPropertyAssertionAxiom(
					parameterIdentifier_dataproperty, condition_individual, parameterIdentifier);
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterIdentifier_assertion);
		}
		if (!conditionParameter.isEmpty()) {
			OWLDataPropertyAssertionAxiom conditionParameter_assertion = factory.getOWLDataPropertyAssertionAxiom(
					conditionParameter_dataproperty, condition_individual, conditionParameter);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionParameter_assertion);
		}
		if (!unit.isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_assertion = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					condition_individual, unit);
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_assertion);
		}

		// Linking ObjectProperty comparisonOperator of Condition
		if (!(comparisonOperator.equals("-"))) {
			OWLNamedIndividual comparisonOperator_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + comparisonOperator));
			OWLObjectProperty comparisonOperator_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasComparisonOperator"));

			OWLObjectPropertyAssertionAxiom comparisonOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					comparisonOperator_objectproperty, condition_individual, comparisonOperator_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), comparisonOperator_assertion);
		}

		// Adding and Linking ObjectProperty TemporalRestriction of Condition
		if (!temporalrestrictionvalue.isEmpty()) {
			OWLObjectProperty temporalrestriction_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalRestriction"));

			OWLObjectPropertyAssertionAxiom temporalrestriction_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					temporalrestriction_objectproperty, condition_individual, temporalrestriction_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestriction_assertion);

			// Adding DataProperty (Temporal Restriction Value) to
			// TemporalRestriction
			OWLDataProperty temporalrestrictionvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#temporalRestrictionValue"));

			if (!(temporalrestrictionvalue.isEmpty())) {
				OWLDatatype temporalrestrictionvalue_type = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral temporalrestrictionvalue_literal = factory.getOWLLiteral(temporalrestrictionvalue,
						temporalrestrictionvalue_type);

				OWLDataPropertyAssertionAxiom temporalrestrictionvalue_assertion = factory
						.getOWLDataPropertyAssertionAxiom(temporalrestrictionvalue_dataproperty,
								temporalrestriction_individual, temporalrestrictionvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalrestrictionvalue_assertion);
			}

			// Linking ObjectProperty TemporalOperator of TemporalRestriction
			if (!(temporaloperator.equals("-"))) {
				OWLNamedIndividual temporalOperator_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporaloperator));
				OWLObjectProperty temporalOperator_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalOperator"));

				OWLObjectPropertyAssertionAxiom temporalOperator_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalOperator_objectproperty, temporalrestriction_individual, temporalOperator_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalOperator_assertion);
			}

			// Linking ObjectProperty TemporalUnit of TemporalRestriction
			if (!(temporalunit.equals("-"))) {
				OWLNamedIndividual temporalunit_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + temporalunit));
				OWLObjectProperty temporalunit_objectproperty = factory
						.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

				OWLObjectPropertyAssertionAxiom temporalunit_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						temporalunit_objectproperty, temporalrestriction_individual, temporalunit_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_assertion);
			}
		}

		JOptionPane.showMessageDialog(null, "Condition was edited successfully.\nCondition_ID: " + individual_name,
				"Clinical Condition edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Condition was edited successfully.\nCondition_ID: " + individual_name);
	}

	private void EditOption(String individual_name, EditOptionPage2 editoptionpage2, ListModel optionconditionset) {
		// Option
		OWLNamedIndividual option_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Data Property
		if (editoptionpage2.option_type == 0) { // NumericalValue ->
												// option_type = 0
			OWLDataProperty numericalvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#numericalValue"));

			if (!(editoptionpage2.numericalvalue_textField.getText().isEmpty())) {
				OWLDatatype Decimal = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral numericalvalue_literal = factory
						.getOWLLiteral(editoptionpage2.numericalvalue_textField.getText(), Decimal);

				OWLDataPropertyAssertionAxiom numericalvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						numericalvalue_dataproperty, option_individual, numericalvalue_literal);
				ontology_manager.addAxiom(manager.getActiveOntology(), numericalvalue_axiom);
			}

		} else { // QualitativeValue -> option_type = 1
			OWLDataProperty qualitativevalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#qualitativeValue"));

			if (!(editoptionpage2.qualitativevalue_textField.getText().isEmpty())) {
				OWLDataPropertyAssertionAxiom qualitativevalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						qualitativevalue_dataproperty, option_individual,
						editoptionpage2.qualitativevalue_textField.getText());

				ontology_manager.addAxiom(manager.getActiveOntology(), qualitativevalue_axiom);
			}
		}
		OWLDataProperty parameteridentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty optionparameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#optionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));

		if (!editoptionpage2.parameteridentifier_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameteridentifier_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameteridentifier_dataproperty, option_individual,
					editoptionpage2.parameteridentifier_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameteridentifier_axiom);
		}
		if (!editoptionpage2.optionparameter_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom optionparameter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					optionparameter_dataproperty, option_individual,
					editoptionpage2.optionparameter_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), optionparameter_axiom);
		}
		if (!editoptionpage2.unit_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_axiom = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					option_individual, editoptionpage2.unit_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_axiom);
		}

		// Object Property - Option ConditionSet
		OWLObjectProperty hasoptionconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOptionConditionSet"));

		OWLNamedIndividual optionconditionset_individual;

		String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

		OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
		optionconditionset_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

		try {
			optionconditionset_individual = option_individual
					.getObjectPropertyValues(hasoptionconditionset_objectproperty, manager.getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			removeAllDataProperties(optionconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(optionconditionset_individual.getIRI().getFragment());

			if (optionconditionset.getSize() == 0) {
				// Remove hasoptionconditionset ObjectProperty
				OWLObjectPropertyAssertionAxiom optionconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						hasoptionconditionset_objectproperty, option_individual, optionconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), optionconditionset_axiom);

				// Remove conditionset Individual
				OWLIndividualAxiom optionconditionset_individual_axiom = factory
						.getOWLClassAssertionAxiom(conditionset_class, optionconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), optionconditionset_individual_axiom);
			}
		} catch (Exception e) {
			if (optionconditionset.getSize() > 0) {
				OWLClassAssertionAxiom conditionset_axiom = factory.getOWLClassAssertionAxiom(conditionset_class,
						optionconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_axiom);

				OWLObjectPropertyAssertionAxiom optionconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						hasoptionconditionset_objectproperty, option_individual, optionconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), optionconditionset_axiom);
			}
		}

		if (optionconditionset.getSize() > 0) {
			OWLObjectProperty hasCondition_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

			for (int i = 0; i < optionconditionset.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + optionconditionset.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, optionconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			// OptionConditionSetCounter
			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(optionconditionset.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, optionconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		JOptionPane.showMessageDialog(null, "Option_ID: " + individual_name + " was successfully edited.",
				"Option edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Option edited with success.\nOption_ID: " + individual_name);

	}

	private void EditParameter(String individual_name, EditParameterPage2 editparameterpage) {
		// Parameter
		OWLNamedIndividual parameter_individual = factory
				.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		removeAllDataProperties(individual_name);

		// Data Property
		OWLDataProperty possiblevalue_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#possibleValue"));
		OWLDataProperty variablename_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#variableName"));
		OWLDataProperty numeric_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#numeric"));
		OWLDataProperty parameteridentifier_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterIdentifier"));
		OWLDataProperty questionparameter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#questionParameter"));
		OWLDataProperty unit_dataproperty = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#unit"));
		OWLDataProperty parameterdescription_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#parameterDescription"));

		// numericValue
		OWLDatatype BooleanType = factory.getOWLDatatype(OWL2Datatype.XSD_BOOLEAN.getIRI());
		String aux = editparameterpage.possiblevalue_textField.getText();
		aux = aux.replaceAll("\\D+", "");
		OWLLiteral numeric_literal;
		if (aux.isEmpty()) {
			numeric_literal = factory.getOWLLiteral("false", BooleanType);
		} else {
			numeric_literal = factory.getOWLLiteral("true", BooleanType);
		}

		if (!editparameterpage.possiblevalue_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom possiblevalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					possiblevalue_dataproperty, parameter_individual,
					editparameterpage.possiblevalue_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), possiblevalue_axiom);

			OWLDataPropertyAssertionAxiom numeric_axiom = factory.getOWLDataPropertyAssertionAxiom(numeric_dataproperty,
					parameter_individual, numeric_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), numeric_axiom);
		}
		if (!editparameterpage.variablename_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom variablename_axiom = factory.getOWLDataPropertyAssertionAxiom(
					variablename_dataproperty, parameter_individual,
					editparameterpage.variablename_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), variablename_axiom);
		}
		if (!editparameterpage.parameteridentifier_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameteridentifier_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameteridentifier_dataproperty, parameter_individual,
					editparameterpage.parameteridentifier_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameteridentifier_axiom);
		}
		if (!editparameterpage.questionparameter_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom questionparameter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					questionparameter_dataproperty, parameter_individual,
					editparameterpage.questionparameter_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), questionparameter_axiom);
		}
		if (!editparameterpage.unit_textField.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom unit_axiom = factory.getOWLDataPropertyAssertionAxiom(unit_dataproperty,
					parameter_individual, editparameterpage.unit_textField.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), unit_axiom);
		}
		if (!editparameterpage.parameterdescription_textArea.getText().isEmpty()) {
			OWLDataPropertyAssertionAxiom parameterdescription_axiom = factory.getOWLDataPropertyAssertionAxiom(
					parameterdescription_dataproperty, parameter_individual,
					editparameterpage.parameterdescription_textArea.getText());
			ontology_manager.addAxiom(manager.getActiveOntology(), parameterdescription_axiom);
		}

		JOptionPane.showMessageDialog(null, "Parameter_ID: " + individual_name + " was successfully edited.",
				"Parameter edited with success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Parameter edited with success.\nParameter_ID: " + individual_name);
	}

	private void generalDescription(OWLNamedIndividual class_individual, String description) {
		// DataProperty generalDescription
		if (!description.isEmpty()) {
			OWLDataProperty generalDescription_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#generalDescription"));
			OWLDataPropertyAssertionAxiom generalDescription_axiom = factory
					.getOWLDataPropertyAssertionAxiom(generalDescription_dataproperty, class_individual, description);
			ontology_manager.addAxiom(manager.getActiveOntology(), generalDescription_axiom);
		}
	}

	private void outcomecondition(OWLNamedIndividual outcome_individual, ListModel outcomeconditions) {
		// hasOutcome
		OWLObjectProperty outcomeconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasOutcomeConditionSet"));

		OWLNamedIndividual outcomeconditionset_individual;

		try {
			outcomeconditionset_individual = outcome_individual
					.getObjectPropertyValues(outcomeconditionset_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			removeAllDataProperties(outcomeconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(outcomeconditionset_individual.getIRI().getFragment());

		} catch (Exception e) {
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

			outcomeconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			OWLClassAssertionAxiom conditionset_classaxiom = factory.getOWLClassAssertionAxiom(conditionset_class,
					outcomeconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_classaxiom);

			OWLObjectPropertyAssertionAxiom outcomeconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					outcomeconditionset_objectproperty, outcome_individual, outcomeconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), outcomeconditionset_axiom);
		}

		OWLObjectProperty hasCondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

		for (int i = 0; i < outcomeconditions.getSize(); i++) {
			OWLNamedIndividual condition_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + outcomeconditions.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					hasCondition_objectproperty, outcomeconditionset_individual, condition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
		}
		// OutcomeConditionSetCounter
		OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		OWLLiteral conditionsetcounter_literal = factory.getOWLLiteral(Integer.toString(outcomeconditions.getSize()),
				IntegerType);

		OWLDataProperty conditionsetcounter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
		OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
				conditionsetcounter_dataproperty, outcomeconditionset_individual, conditionsetcounter_literal);
		ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
	}

	private void triggercondition(OWLNamedIndividual triggercondition_individual, ListModel triggerconditions) {
		// TriggerCondition
		OWLObjectProperty triggerconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTriggerConditionSet"));

		OWLNamedIndividual triggerconditionset_individual;
		try {
			triggerconditionset_individual = triggercondition_individual
					.getObjectPropertyValues(triggerconditionset_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			removeAllDataProperties(triggerconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(triggerconditionset_individual.getIRI().getFragment());

		} catch (Exception e) {
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			triggerconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			OWLClassAssertionAxiom conditionset_classaxiom = factory.getOWLClassAssertionAxiom(conditionset_class,
					triggerconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_classaxiom);

			OWLObjectPropertyAssertionAxiom triggerconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					triggerconditionset_objectproperty, triggercondition_individual, triggerconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), triggerconditionset_axiom);
		}

		OWLObjectProperty hasCondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));
		for (int i = 0; i < triggerconditions.getSize(); i++) {
			OWLNamedIndividual condition_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + triggerconditions.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					hasCondition_objectproperty, triggerconditionset_individual, condition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
		}
		// TriggerConditionSetCounter
		OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		OWLLiteral conditionsetcounter_literal = factory.getOWLLiteral(Integer.toString(triggerconditions.getSize()),
				IntegerType);

		OWLDataProperty conditionsetcounter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
		OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
				conditionsetcounter_dataproperty, triggerconditionset_individual, conditionsetcounter_literal);
		ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
	}

	private void precondition(OWLNamedIndividual precondition_individual, ListModel preconditions) {
		// PreCondition
		OWLObjectProperty preconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPreConditionSet"));

		OWLNamedIndividual preconditionset_individual;

		try {
			preconditionset_individual = precondition_individual
					.getObjectPropertyValues(preconditionset_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			removeAllDataProperties(preconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(preconditionset_individual.getIRI().getFragment());

		} catch (Exception e) {
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

			preconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			OWLClassAssertionAxiom conditionset_classaxiom = factory.getOWLClassAssertionAxiom(conditionset_class,
					preconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionset_classaxiom);

			OWLObjectPropertyAssertionAxiom preconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					preconditionset_objectproperty, precondition_individual, preconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), preconditionset_axiom);
		}

		OWLObjectProperty hasCondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

		for (int i = 0; i < preconditions.getSize(); i++) {
			OWLNamedIndividual condition_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + preconditions.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
					hasCondition_objectproperty, preconditionset_individual, condition_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
		}
		// PreConditionSetCounter
		OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		OWLLiteral conditionsetcounter_literal = factory.getOWLLiteral(Integer.toString(preconditions.getSize()),
				IntegerType);

		OWLDataProperty conditionsetcounter_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
		OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
				conditionsetcounter_dataproperty, preconditionset_individual, conditionsetcounter_literal);
		ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
	}

	private void stopconditiontask(OWLNamedIndividual class_individual, String stop_clinicaltask) {
		// StopCondition Task
		OWLObjectProperty stopconditiontask_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#stopConditionTask"));

		OWLNamedIndividual stopconditiontask_individual;
		try { // Remove if allready exists 1 StopCondition Task
			stopconditiontask_individual = class_individual
					.getObjectPropertyValues(stopconditiontask_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			OWLObjectPropertyAssertionAxiom stopconditiontask_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(stopconditiontask_objectproperty, class_individual,
							stopconditiontask_individual);

			ontology_manager.removeAxiom(manager.getActiveOntology(), stopconditiontask_objectpropertyaxiom);
		} catch (Exception e) {
		} // Nothing found

		if (!(stop_clinicaltask.equals("-"))) {
			stopconditiontask_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + stop_clinicaltask));
			OWLObjectPropertyAssertionAxiom stopconditiontask_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					stopconditiontask_objectproperty, class_individual, stopconditiontask_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), stopconditiontask_axiom);
		}
	}

	private void synctask(OWLNamedIndividual class_individual, String synctask) {
		// SyncTask
		OWLObjectProperty syncTask_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#syncTask"));

		OWLNamedIndividual syncTask_individual;

		try { // Remove if allready exists 1 synctask
			syncTask_individual = class_individual
					.getObjectPropertyValues(syncTask_objectproperty, manager.getActiveOntology()).iterator().next()
					.asOWLNamedIndividual();
			OWLObjectPropertyAssertionAxiom syncTask_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(syncTask_objectproperty, class_individual, syncTask_individual);

			ontology_manager.removeAxiom(manager.getActiveOntology(), syncTask_objectpropertyaxiom);
		} catch (Exception e) {
		} // Nothing found

		if (!(synctask.equals("-"))) {
			syncTask_individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + synctask));
			OWLObjectPropertyAssertionAxiom syncTask_axiom = factory
					.getOWLObjectPropertyAssertionAxiom(syncTask_objectproperty, class_individual, syncTask_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), syncTask_axiom);
		}
	}

	private void firsttask(OWLNamedIndividual class_individual, ListModel firsttasks) {
		// HasFirstTask
		OWLObjectProperty hasfirsttask_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasFirstTask"));

		try { // Remove if allready exists 1 synctask
			Set<OWLIndividual> hasfirsttask_individuals = class_individual
					.getObjectPropertyValues(hasfirsttask_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : hasfirsttask_individuals) {
				OWLObjectPropertyAssertionAxiom hasfirsttask_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasfirsttask_objectproperty, class_individual, a);
				ontology_manager.removeAxiom(manager.getActiveOntology(), hasfirsttask_objectpropertyaxiom);
			}
		} catch (Exception e) {
		} // Nothing found

		for (int i = 0; i < firsttasks.getSize(); i++) {
			OWLNamedIndividual task_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + firsttasks.getElementAt(i)));
			OWLObjectPropertyAssertionAxiom task_axiom = factory
					.getOWLObjectPropertyAssertionAxiom(hasfirsttask_objectproperty, class_individual, task_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), task_axiom);
		}
	}

	private void nexttask(OWLNamedIndividual class_individual, int nexttype, ListModel alternativetask, String next,
			ListModel parallel, ListModel preferencealternative) {
		// Next task
		OWLObjectProperty paralleltask_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#parallelTask"));

		OWLObjectProperty preferencealternative_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#preferenceAlternativeTask"));

		OWLObjectProperty alternativetask_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#alternativeTask"));

		OWLObjectProperty nexttask_objectproperty = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#nextTask"));

		// Remove nexttasks if exists
		// ParallelTask
		try {
			Set<OWLIndividual> nexttask_individuals = class_individual
					.getObjectPropertyValues(paralleltask_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : nexttask_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(paralleltask_objectproperty, class_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}

		// PreferenceAlternativeTask
		try {
			Set<OWLIndividual> nexttask_individuals = class_individual
					.getObjectPropertyValues(preferencealternative_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : nexttask_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(preferencealternative_objectproperty, class_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}

		// AlternativeTask
		try {
			Set<OWLIndividual> nexttask_individuals = class_individual
					.getObjectPropertyValues(alternativetask_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : nexttask_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(alternativetask_objectproperty, class_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}

		// NextTask
		try {
			Set<OWLIndividual> nexttask_individuals = class_individual.getObjectPropertyValues(nexttask_objectproperty,
					manager.getActiveOntology());
			for (OWLIndividual a : nexttask_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(nexttask_objectproperty, class_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}
		} catch (Exception e) {
		}

		// Insert nexttask
		if (nexttype == 1) {

			for (int i = 0; i < parallel.getSize(); i++) {
				OWLNamedIndividual task_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + parallel.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom task_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						paralleltask_objectproperty, class_individual, task_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), task_axiom);
			}
		} else if (nexttype == 2) {

			for (int i = 0; i < preferencealternative.getSize(); i++) {
				OWLNamedIndividual task_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + preferencealternative.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom task_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						preferencealternative_objectproperty, class_individual, task_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), task_axiom);
			}
		} else if (nexttype == 3) {

			for (int i = 0; i < alternativetask.getSize(); i++) {
				OWLNamedIndividual task_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + alternativetask.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom task_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						alternativetask_objectproperty, class_individual, task_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), task_axiom);
			}
		} else if (nexttype == 4) {
			if (!(next.equals("-"))) {

				OWLNamedIndividual task_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + next));
				OWLObjectPropertyAssertionAxiom task_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(nexttask_objectproperty, class_individual, task_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), task_axiom);
			}
		}
	}

	private void periodicity(OWLNamedIndividual periodicity_individual, CreateActionPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions) {
		// Handle periodicity data property
		if (periodicity_restrictions.periodicity_type == 0) { // Max/Min
			OWLDataProperty maxperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxPeriodicityValue"));
			OWLDataProperty minperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minPeriodicityValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
			if (!(periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral maxperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.maxperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxperiodicityvalue_dataproperty, periodicity_individual, maxperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxperiodicityvalue_axiom);
			}
			if (!(periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral minperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.minperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom minperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						minperiodicityvalue_dataproperty, periodicity_individual, minperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), minperiodicityvalue_axiom);
			}

		} else { // Average
			OWLDataProperty periodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#periodicityValue"));

			if (!(periodicity_restrictions.periodicity_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral periodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.periodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom periodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						periodicityvalue_dataproperty, periodicity_individual, periodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), periodicityvalue_axiom);
			}
		}
		if (!(periodicity_restrictions.repetitionvalue_textfield.getText().isEmpty())) {
			OWLDataProperty repetitionValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#repetitionValue"));

			OWLDatatype integerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral repetitionValue_literal = factory
					.getOWLLiteral(periodicity_restrictions.repetitionvalue_textfield.getText(), integerType);

			OWLDataPropertyAssertionAxiom repetitionValue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					repetitionValue_dataproperty, periodicity_individual, repetitionValue_literal);

			ontology_manager.addAxiom(manager.getActiveOntology(), repetitionValue_axiom);
		}

		// Handle periodicity objectproperty

		// Create ConditionSet
		if (periodicity_stop_restrictions.getSize() > 0) {
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			OWLNamedIndividual stopconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			// Link Individual to OWLClass
			OWLClassAssertionAxiom stopconditionset_classAssertion = factory
					.getOWLClassAssertionAxiom(conditionset_class, stopconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_classAssertion);

			// Link Periodicity and ConditionSet
			OWLObjectProperty stopconditionset_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));
			OWLObjectPropertyAssertionAxiom stopconditionset_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(stopconditionset_objectproperty, periodicity_individual,
							stopconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_objectpropertyaxiom);

			OWLObjectProperty hasCondition_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

			for (int i = 0; i < periodicity_stop_restrictions.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory.getOWLNamedIndividual(
						IRI.create(ontologyIRI + "#" + periodicity_stop_restrictions.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, stopconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(periodicity_stop_restrictions.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, stopconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		// hasTemporalUnit
		if (periodicity_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty hasTemporalUnit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) periodicity_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasTemporalUnit_objectproperty, periodicity_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasTemporalUnit_axiom);
		}
	}

	private void periodicity(OWLNamedIndividual periodicity_individual, CreatePlanPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions) {
		// Handle periodicity data property
		if (periodicity_restrictions.periodicity_type == 0) { // Max/Min
			OWLDataProperty maxperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxPeriodicityValue"));
			OWLDataProperty minperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minPeriodicityValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral maxperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.maxperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxperiodicityvalue_dataproperty, periodicity_individual, maxperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxperiodicityvalue_axiom);
			}

			if (!(periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral minperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.minperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom minperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						minperiodicityvalue_dataproperty, periodicity_individual, minperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), minperiodicityvalue_axiom);
			}

		} else { // Average
			OWLDataProperty periodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#periodicityValue"));

			if (!(periodicity_restrictions.periodicity_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral periodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.periodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom periodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						periodicityvalue_dataproperty, periodicity_individual, periodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), periodicityvalue_axiom);
			}
		}

		if (!(periodicity_restrictions.repetitionvalue_textfield.getText().isEmpty())) {
			OWLDataProperty repetitionValue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#repetitionValue"));

			OWLDatatype integerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral repetitionValue_literal = factory
					.getOWLLiteral(periodicity_restrictions.repetitionvalue_textfield.getText(), integerType);

			OWLDataPropertyAssertionAxiom repetitionValue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					repetitionValue_dataproperty, periodicity_individual, repetitionValue_literal);

			ontology_manager.addAxiom(manager.getActiveOntology(), repetitionValue_axiom);
		}

		// Handle periodicity objectproperty

		if (periodicity_stop_restrictions.getSize() > 0) {
			// Create ConditionSet
			String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

			OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
			OWLNamedIndividual stopconditionset_individual = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

			// Link Individual to OWLClass
			OWLClassAssertionAxiom stopconditionset_classAssertion = factory
					.getOWLClassAssertionAxiom(conditionset_class, stopconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_classAssertion);

			// Link Periodicity and ConditionSet
			OWLObjectProperty stopconditionset_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));
			OWLObjectPropertyAssertionAxiom stopconditionset_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(stopconditionset_objectproperty, periodicity_individual,
							stopconditionset_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_objectpropertyaxiom);

			OWLObjectProperty hasCondition_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

			for (int i = 0; i < periodicity_stop_restrictions.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory.getOWLNamedIndividual(
						IRI.create(ontologyIRI + "#" + periodicity_stop_restrictions.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, stopconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(periodicity_stop_restrictions.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, stopconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		// hasTemporalUnit
		if (periodicity_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty hasTemporalUnit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) periodicity_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasTemporalUnit_objectproperty, periodicity_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasTemporalUnit_axiom);
		}
	}

	private void periodicity(OWLNamedIndividual periodicity_individual, EditActionPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions) {

		OWLObjectProperty hasTemporalUnit_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

		OWLObjectProperty stopconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));

		OWLObjectProperty hasCondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

		// Remove Periodicity Data and Object Properties (maintain conditionset
		// if exists)

		// Remove Data Properties of Periodicity
		removeAllDataProperties(periodicity_individual.getIRI().getFragment());
		try {

			// Remove TemporalUnit
			OWLNamedIndividual hasTemporalUnit_individual = periodicity_individual
					.getObjectPropertyValues(hasTemporalUnit_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(hasTemporalUnit_objectproperty, periodicity_individual,
							hasTemporalUnit_individual);

			ontology_manager.removeAxiom(manager.getActiveOntology(), hasTemporalUnit_objectpropertyaxiom);

		} catch (Exception e) {
		}

		OWLNamedIndividual stopconditionset_individual = null;
		try {
			// Handle ConditionSet (Remove All Conditions and ConditionsCounter)
			stopconditionset_individual = periodicity_individual
					.getObjectPropertyValues(stopconditionset_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			removeAllDataProperties(stopconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(stopconditionset_individual.getIRI().getFragment());

			if (periodicity_stop_restrictions.getSize() == 0) {
				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remover Assertion entre Periodicity e StopConditionSet
				OWLObjectPropertyAssertionAxiom stopconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						stopconditionset_objectproperty, periodicity_individual, stopconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), stopconditionset_axiom);

				// Remove Duration Individual
				stopconditionset_individual.accept(remover);
			}
		} catch (Exception e) { // ConditionSet doesn't exist
			if (periodicity_stop_restrictions.getSize() > 0) {
				// Create ConditionSet
				String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

				OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
				stopconditionset_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

				// Link Individual to OWLClass
				OWLClassAssertionAxiom stopconditionset_classAssertion = factory
						.getOWLClassAssertionAxiom(conditionset_class, stopconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_classAssertion);

				// Link Periodicity and ConditionSet
				OWLObjectPropertyAssertionAxiom stopconditionset_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(stopconditionset_objectproperty, periodicity_individual,
								stopconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_objectpropertyaxiom);
			}
		}

		// Handle periodicity data property
		if (periodicity_restrictions.periodicity_type == 0) { // Max/Min
			OWLDataProperty maxperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxPeriodicityValue"));
			OWLDataProperty minperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minPeriodicityValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral maxperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.maxperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxperiodicityvalue_dataproperty, periodicity_individual, maxperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxperiodicityvalue_axiom);
			}
			if (!(periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral minperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.minperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom minperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						minperiodicityvalue_dataproperty, periodicity_individual, minperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), minperiodicityvalue_axiom);
			}

		} else { // Average
			OWLDataProperty periodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#periodicityValue"));

			if (!(periodicity_restrictions.periodicity_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral periodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.periodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom periodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						periodicityvalue_dataproperty, periodicity_individual, periodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), periodicityvalue_axiom);
			}
		}
		OWLDataProperty repetitionValue_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#repetitionValue"));

		if (!(periodicity_restrictions.repetitionvalue_textfield.getText().isEmpty())) {

			OWLDatatype integerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral repetitionValue_literal = factory
					.getOWLLiteral(periodicity_restrictions.repetitionvalue_textfield.getText(), integerType);

			OWLDataPropertyAssertionAxiom repetitionValue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					repetitionValue_dataproperty, periodicity_individual, repetitionValue_literal);

			ontology_manager.addAxiom(manager.getActiveOntology(), repetitionValue_axiom);

		}

		// Handle periodicity objectproperty
		if (periodicity_stop_restrictions.getSize() > 0) {
			for (int i = 0; i < periodicity_stop_restrictions.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory.getOWLNamedIndividual(
						IRI.create(ontologyIRI + "#" + periodicity_stop_restrictions.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, stopconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(periodicity_stop_restrictions.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, stopconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		// hasTemporalUnit
		if (periodicity_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) periodicity_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasTemporalUnit_objectproperty, periodicity_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasTemporalUnit_axiom);
		}
	}

	private void periodicity(OWLNamedIndividual periodicity_individual, EditPlanPage3 periodicity_restrictions,
			ListModel periodicity_stop_restrictions) {

		OWLObjectProperty hasTemporalUnit_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));

		OWLObjectProperty stopconditionset_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasStopConditionSet"));

		OWLObjectProperty hasCondition_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasCondition"));

		// Remove Periodicity Data and Object Properties (maintain conditionset
		// if exists)

		// Remove Data Properties of Periodicity
		removeAllDataProperties(periodicity_individual.getIRI().getFragment());
		try {

			// Remove TemporalUnit
			OWLNamedIndividual hasTemporalUnit_individual = periodicity_individual
					.getObjectPropertyValues(hasTemporalUnit_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_objectpropertyaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(hasTemporalUnit_objectproperty, periodicity_individual,
							hasTemporalUnit_individual);

			ontology_manager.removeAxiom(manager.getActiveOntology(), hasTemporalUnit_objectpropertyaxiom);

		} catch (Exception e) {
		}

		OWLNamedIndividual stopconditionset_individual = null;
		try {
			// Handle ConditionSet (Remove All Conditions and ConditionsCounter)
			stopconditionset_individual = periodicity_individual
					.getObjectPropertyValues(stopconditionset_objectproperty, manager.getActiveOntology()).iterator()
					.next().asOWLNamedIndividual();

			removeAllDataProperties(stopconditionset_individual.getIRI().getFragment());
			removeAllObjectProperties(stopconditionset_individual.getIRI().getFragment());

			if (periodicity_stop_restrictions.getSize() == 0) {
				// Create OWLEntityRemover
				OWLEntityRemover remover = new OWLEntityRemover(ontology_manager,
						Collections.singleton(manager.getActiveOntology()));

				// Remover Assertion entre Periodicity e StopConditionSet
				OWLObjectPropertyAssertionAxiom stopconditionset_axiom = factory.getOWLObjectPropertyAssertionAxiom(
						stopconditionset_objectproperty, periodicity_individual, stopconditionset_individual);
				ontology_manager.removeAxiom(manager.getActiveOntology(), stopconditionset_axiom);

				// Remove Duration Individual
				stopconditionset_individual.accept(remover);
			}

		} catch (Exception e) { // ConditionSet doesn't exist
			if (periodicity_stop_restrictions.getSize() > 0) {
				// Create ConditionSet
				String name_conditionset = "CS" + Integer.toString(getlastindividualnumber(ConditionSet));

				OWLClass conditionset_class = factory.getOWLClass(ConditionSet);
				stopconditionset_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name_conditionset));

				// Link Individual to OWLClass
				OWLClassAssertionAxiom stopconditionset_classAssertion = factory
						.getOWLClassAssertionAxiom(conditionset_class, stopconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_classAssertion);

				// Link Periodicity and ConditionSet
				OWLObjectPropertyAssertionAxiom stopconditionset_objectpropertyaxiom = factory
						.getOWLObjectPropertyAssertionAxiom(stopconditionset_objectproperty, periodicity_individual,
								stopconditionset_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), stopconditionset_objectpropertyaxiom);
			}
		}

		// Handle periodicity data property
		if (periodicity_restrictions.periodicity_type == 0) { // Max/Min
			OWLDataProperty maxperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxPeriodicityValue"));
			OWLDataProperty minperiodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minPeriodicityValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(periodicity_restrictions.maxperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral maxperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.maxperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxperiodicityvalue_dataproperty, periodicity_individual, maxperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxperiodicityvalue_axiom);
			}

			if (!(periodicity_restrictions.minperiodicity_textfield.getText().isEmpty())) {
				OWLLiteral minperiodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.minperiodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom minperiodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						minperiodicityvalue_dataproperty, periodicity_individual, minperiodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), minperiodicityvalue_axiom);
			}

		} else { // Average
			OWLDataProperty periodicityvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#periodicityValue"));

			if (!(periodicity_restrictions.periodicity_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral periodicityvalue_literal = factory
						.getOWLLiteral(periodicity_restrictions.periodicity_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom periodicityvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						periodicityvalue_dataproperty, periodicity_individual, periodicityvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), periodicityvalue_axiom);
			}
		}
		OWLDataProperty repetitionValue_dataproperty = factory
				.getOWLDataProperty(IRI.create(ontologyIRI + "#repetitionValue"));

		if (!(periodicity_restrictions.repetitionvalue_textfield.getText().isEmpty())) {
			OWLDatatype integerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral repetitionValue_literal = factory
					.getOWLLiteral(periodicity_restrictions.repetitionvalue_textfield.getText(), integerType);

			OWLDataPropertyAssertionAxiom repetitionValue_axiom = factory.getOWLDataPropertyAssertionAxiom(
					repetitionValue_dataproperty, periodicity_individual, repetitionValue_literal);

			ontology_manager.addAxiom(manager.getActiveOntology(), repetitionValue_axiom);
		}

		// Handle periodicity objectproperty
		if (periodicity_stop_restrictions.getSize() > 0) {
			for (int i = 0; i < periodicity_stop_restrictions.getSize(); i++) {
				OWLNamedIndividual condition_individual = factory.getOWLNamedIndividual(
						IRI.create(ontologyIRI + "#" + periodicity_stop_restrictions.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom condition_assertion = factory.getOWLObjectPropertyAssertionAxiom(
						hasCondition_objectproperty, stopconditionset_individual, condition_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), condition_assertion);
			}

			OWLDatatype IntegerType = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
			OWLLiteral conditionsetcounter_literal = factory
					.getOWLLiteral(Integer.toString(periodicity_stop_restrictions.getSize()), IntegerType);

			OWLDataProperty conditionsetcounter_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#conditionSetCounter"));
			OWLDataPropertyAssertionAxiom conditionsetcounter_axiom = factory.getOWLDataPropertyAssertionAxiom(
					conditionsetcounter_dataproperty, stopconditionset_individual, conditionsetcounter_literal);
			ontology_manager.addAxiom(manager.getActiveOntology(), conditionsetcounter_axiom);
		}

		// hasTemporalUnit
		if (periodicity_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) periodicity_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom hasTemporalUnit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					hasTemporalUnit_objectproperty, periodicity_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), hasTemporalUnit_axiom);
		}
	}

	private void duration(OWLNamedIndividual duration_individual, CreateActionPage10 duration_restrictions) {

		// Duration DataProperty
		if (duration_restrictions.duration_type == 0) { // Max/Min
			OWLDataProperty maxdurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxDurationValue"));
			OWLDataProperty mindurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minDurationValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(duration_restrictions.maxduration_textfield.getText().isEmpty())) {
				OWLLiteral maxdurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.maxduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxdurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxdurationvalue_dataproperty, duration_individual, maxdurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxdurationvalue_axiom);
			}
			if (!(duration_restrictions.minduration_textfield.getText().isEmpty())) {
				OWLLiteral mindurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.minduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom mindurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						mindurationvalue_dataproperty, duration_individual, mindurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), mindurationvalue_axiom);
			}

		} else { // Average
			OWLDataProperty durationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#durationValue"));

			if (!(duration_restrictions.duration_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral durationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.duration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom durationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						durationvalue_dataproperty, duration_individual, durationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), durationvalue_axiom);
			}
		}
		// Duration TemporalUnit
		if (duration_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty temporalunit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) duration_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom temporalunit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					temporalunit_objectproperty, duration_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_axiom);
		}
	}

	private void duration(OWLNamedIndividual duration_individual, CreatePlanPage10 duration_restrictions) {
		// Duration DataProperty
		if (duration_restrictions.duration_type == 0) { // Max/Min
			OWLDataProperty maxdurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxDurationValue"));
			OWLDataProperty mindurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minDurationValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(duration_restrictions.maxduration_textfield.getText().isEmpty())) {
				OWLLiteral maxdurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.maxduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxdurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxdurationvalue_dataproperty, duration_individual, maxdurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxdurationvalue_axiom);
			}

			if (!(duration_restrictions.minduration_textfield.getText().isEmpty())) {
				OWLLiteral mindurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.minduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom mindurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						mindurationvalue_dataproperty, duration_individual, mindurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), mindurationvalue_axiom);
			}

		} else { // Average
			OWLDataProperty durationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#durationValue"));

			if (!(duration_restrictions.duration_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral durationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.duration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom durationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						durationvalue_dataproperty, duration_individual, durationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), durationvalue_axiom);
			}
		}
		// Duration TemporalUnit
		if (duration_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty temporalunit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) duration_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom temporalunit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					temporalunit_objectproperty, duration_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_axiom);
		}
	}

	private void duration(OWLNamedIndividual duration_individual, EditActionPage10 duration_restrictions) {
		// Remove Data and Object Properties of Duration if exists
		try {
			removeAllDataProperties(duration_individual.getIRI().getFragment());
			removeAllObjectProperties(duration_individual.getIRI().getFragment());
		} catch (Exception e) {
		}
		// Duration DataProperty
		if (duration_restrictions.duration_type == 0) { // Max/Min
			OWLDataProperty maxdurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxDurationValue"));
			OWLDataProperty mindurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minDurationValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(duration_restrictions.maxduration_textfield.getText().isEmpty())) {
				OWLLiteral maxdurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.maxduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxdurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxdurationvalue_dataproperty, duration_individual, maxdurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxdurationvalue_axiom);
			}

			if (!(duration_restrictions.minduration_textfield.getText().isEmpty())) {
				OWLLiteral mindurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.minduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom mindurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						mindurationvalue_dataproperty, duration_individual, mindurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), mindurationvalue_axiom);
			}

		} else { // Average
			OWLDataProperty durationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#durationValue"));

			if (!(duration_restrictions.duration_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral durationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.duration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom durationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						durationvalue_dataproperty, duration_individual, durationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), durationvalue_axiom);
			}
		}
		// Duration TemporalUnit
		if (duration_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty temporalunit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) duration_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom temporalunit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					temporalunit_objectproperty, duration_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_axiom);
		}
	}

	private void duration(OWLNamedIndividual duration_individual, EditPlanPage10 duration_restrictions) {
		// Remove Data and Object Properties of Duration if exists
		try {
			removeAllDataProperties(duration_individual.getIRI().getFragment());
			removeAllObjectProperties(duration_individual.getIRI().getFragment());
		} catch (Exception e) {
		}
		// Duration DataProperty
		if (duration_restrictions.duration_type == 0) { // Max/Min
			OWLDataProperty maxdurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#maxDurationValue"));
			OWLDataProperty mindurationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#minDurationValue"));

			OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());

			if (!(duration_restrictions.maxduration_textfield.getText().isEmpty())) {
				OWLLiteral maxdurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.maxduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom maxdurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						maxdurationvalue_dataproperty, duration_individual, maxdurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), maxdurationvalue_axiom);
			}

			if (!(duration_restrictions.minduration_textfield.getText().isEmpty())) {
				OWLLiteral mindurationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.minduration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom mindurationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						mindurationvalue_dataproperty, duration_individual, mindurationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), mindurationvalue_axiom);
			}

		} else { // Average
			OWLDataProperty durationvalue_dataproperty = factory
					.getOWLDataProperty(IRI.create(ontologyIRI + "#durationValue"));

			if (!(duration_restrictions.duration_textfield.getText().isEmpty())) {
				OWLDatatype decimalType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
				OWLLiteral durationvalue_literal = factory
						.getOWLLiteral(duration_restrictions.duration_textfield.getText(), decimalType);

				OWLDataPropertyAssertionAxiom durationvalue_axiom = factory.getOWLDataPropertyAssertionAxiom(
						durationvalue_dataproperty, duration_individual, durationvalue_literal);

				ontology_manager.addAxiom(manager.getActiveOntology(), durationvalue_axiom);
			}
		}
		// Duration TemporalUnit
		if (duration_restrictions.temporalunit_combobox.getSelectedIndex() != 0) {
			OWLObjectProperty temporalunit_objectproperty = factory
					.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasTemporalUnit"));
			OWLNamedIndividual temporalunit_individual = factory.getOWLNamedIndividual(IRI.create(
					ontologyIRI + "#" + (String) duration_restrictions.temporalunit_combobox.getSelectedItem()));
			OWLObjectPropertyAssertionAxiom temporalunit_axiom = factory.getOWLObjectPropertyAssertionAxiom(
					temporalunit_objectproperty, duration_individual, temporalunit_individual);
			ontology_manager.addAxiom(manager.getActiveOntology(), temporalunit_axiom);
		}
	}

	private void clinicalactiontype(OWLNamedIndividual action_individual, int choice, ListModel exam, ListModel formula,
			ListModel procedure, ListModel medicalrecommendation, ListModel nonmedicalrecommendation) {
		// ClinicalActionType
		OWLObjectProperty hasclinicalactiontype_objectproperty = factory
				.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasClinicalActionType"));

		// Remove ClinicalAction if exists
		try {
			Set<OWLIndividual> clinicalactiontype_individuals = action_individual
					.getObjectPropertyValues(hasclinicalactiontype_objectproperty, manager.getActiveOntology());
			for (OWLIndividual a : clinicalactiontype_individuals) {
				OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual, a);

				ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
			}

		} catch (Exception e) {
		}

		// Insert ClinicalActions
		if (choice == 1) {

			for (int i = 0; i < exam.getSize(); i++) {
				OWLNamedIndividual clinicalactiontype_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + exam.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom hasclinicalactiontype_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual,
								clinicalactiontype_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasclinicalactiontype_axiom);
			}
		}

		else if (choice == 2) {

			for (int i = 0; i < formula.getSize(); i++) {
				OWLNamedIndividual clinicalactiontype_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + formula.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom hasclinicalactiontype_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual,
								clinicalactiontype_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasclinicalactiontype_axiom);
			}
		}

		else if (choice == 3) {

			for (int i = 0; i < procedure.getSize(); i++) {
				OWLNamedIndividual clinicalactiontype_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + procedure.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom hasclinicalactiontype_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual,
								clinicalactiontype_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasclinicalactiontype_axiom);
			}
		}

		else if (choice == 4) {

			for (int i = 0; i < medicalrecommendation.getSize(); i++) {
				OWLNamedIndividual clinicalactiontype_individual = factory
						.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + medicalrecommendation.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom hasclinicalactiontype_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual,
								clinicalactiontype_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasclinicalactiontype_axiom);
			}
		}

		else if (choice == 5) {

			for (int i = 0; i < nonmedicalrecommendation.getSize(); i++) {
				OWLNamedIndividual clinicalactiontype_individual = factory.getOWLNamedIndividual(
						IRI.create(ontologyIRI + "#" + nonmedicalrecommendation.getElementAt(i)));
				OWLObjectPropertyAssertionAxiom hasclinicalactiontype_axiom = factory
						.getOWLObjectPropertyAssertionAxiom(hasclinicalactiontype_objectproperty, action_individual,
								clinicalactiontype_individual);
				ontology_manager.addAxiom(manager.getActiveOntology(), hasclinicalactiontype_axiom);
			}
		}
	}

	private void updateCPG(String individual_name) {

		Set<OWLIndividual> set_cpg = new HashSet<OWLIndividual>();

		OWLClass cs = factory.getOWLClass(IRI.create(ontologyIRI + "#ClinicalPracticeGuideline"));

		set_cpg = cs.getIndividuals(manager.getActiveOntology());

		OWLDataProperty dateoflastupdate = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#dateOfLastUpdate"));
		OWLDataProperty versionnumber = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#versionNumber"));
		OWLObjectProperty hasPlan = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#hasPlan"));

		for (OWLIndividual a : set_cpg) {
			OWLNamedIndividual cpg_individual = a.asOWLNamedIndividual();

			try {
				OWLNamedIndividual Plan = cpg_individual.getObjectPropertyValues(hasPlan, manager.getActiveOntology())
						.iterator().next().asOWLNamedIndividual();
				if (Plan.getIRI().getFragment().equals(individual_name)) {
					// Remove DateofLastUpdate + Version Number

					try {
						OWLLiteral literal = cpg_individual
								.getDataPropertyValues(dateoflastupdate, manager.getActiveOntology()).iterator().next();
						OWLDataPropertyAssertionAxiom dataproperty_axiom = factory
								.getOWLDataPropertyAssertionAxiom(dateoflastupdate, cpg_individual, literal);

						ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

					} catch (Exception e) {
					}

					String version_number_string = new String("1.0");
					int version_number_int = 0;
					try {
						OWLLiteral versionnumber_literal = cpg_individual
								.getDataPropertyValues(versionnumber, manager.getActiveOntology()).iterator().next();

						version_number_string = versionnumber_literal.getLiteral();
						String version_number_string_ant = new String(version_number_string);

						version_number_string = version_number_string.replaceAll("^(\\d)+(.){1}", "");
						version_number_string_ant = version_number_string_ant.replaceAll("(.){1}(\\d)+", "");
						version_number_int = Integer.parseInt(version_number_string);
						version_number_int++;

						version_number_string = version_number_string_ant + "." + Integer.toString(version_number_int);

						OWLDataPropertyAssertionAxiom dataproperty_axiom = factory
								.getOWLDataPropertyAssertionAxiom(versionnumber, cpg_individual, versionnumber_literal);

						ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);

					} catch (Exception e) {
					}

					// Insert Data Properties in CPG Individual

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
					Date dateobj = new Date();

					String date = df.format(dateobj).toString() + "T" + df1.format(dateobj).toString();

					OWLDatatype dateTimeType = factory.getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
					OWLLiteral dateLeve = factory.getOWLLiteral(date, dateTimeType);

					OWLDatatype versionType = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
					OWLLiteral version = factory.getOWLLiteral(version_number_string, versionType);

					OWLDataPropertyAssertionAxiom dateoflastupdate_assertion = factory
							.getOWLDataPropertyAssertionAxiom(dateoflastupdate, cpg_individual, dateLeve);
					OWLDataPropertyAssertionAxiom versionnumber_assertion = factory
							.getOWLDataPropertyAssertionAxiom(versionnumber, cpg_individual, version);

					// Add Assertion Axiom to the OWL file
					ontology_manager.addAxiom(manager.getActiveOntology(), versionnumber_assertion);
					ontology_manager.addAxiom(manager.getActiveOntology(), dateoflastupdate_assertion);
				}
			} catch (Exception e) {
			}

		}
	}

	private void removeAllDataProperties(String individual_name) {
		OWLNamedIndividual individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
				.getDataPropertyValues(manager.getActiveOntology());
		for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {
			try {
				OWLDataProperty dataproperty = entry.getKey().asOWLDataProperty();
				for (OWLLiteral a : entry.getValue()) {
					OWLDataPropertyAssertionAxiom dataproperty_axiom = factory
							.getOWLDataPropertyAssertionAxiom(dataproperty, individual, a);

					ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
				}
			} catch (Exception e) {
			}
		}
	}

	private void removeAllObjectProperties(String individual_name) {
		OWLNamedIndividual individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + individual_name));

		Map<OWLObjectPropertyExpression, Set<OWLIndividual>> data = individual
				.getObjectPropertyValues(manager.getActiveOntology());
		for (Map.Entry<OWLObjectPropertyExpression, Set<OWLIndividual>> entry : data.entrySet()) {
			try {
				OWLObjectProperty dataproperty = entry.getKey().asOWLObjectProperty();
				for (OWLIndividual a : entry.getValue()) {
					OWLObjectPropertyAssertionAxiom dataproperty_axiom = factory
							.getOWLObjectPropertyAssertionAxiom(dataproperty, individual, a);

					ontology_manager.removeAxiom(manager.getActiveOntology(), dataproperty_axiom);
				}
			} catch (Exception e) {
			}
		}
	}

	private int getlastindividualnumber(IRI owlclasse) {
		// res = 1 caso nao exista
		int res = 0, aux;
		Set<OWLIndividual> individuals = new HashSet<OWLIndividual>();
		OWLOntology ontology = manager.getActiveOntology();

		OWLReasoner reasoner = manager.getReasoner();

		OWLClass cs = manager.getOWLDataFactory().getOWLClass(owlclasse);

		individuals = cs.getIndividuals(ontology);

		for (OWLIndividual a : individuals) {

			try {
				// Extrair os digitos/numeros do nome do OWLIndividual e passar
				// para Inteiro
				aux = Integer.parseInt(a.asOWLNamedIndividual().getIRI().getFragment().replaceAll("\\D+", ""));
				// Obter o maior numero ID do individual
				if (aux > res) {
					res = aux;
				}
			} catch (Exception e) { // Caso OWLNamedIndividual nao apresente
									// digitos, nao e contabilizado

			}
		}

		return (res + 1);
	}

	private void updatestate() {
		ontologyIRI = manager.getActiveOntology().getOntologyID().getOntologyIRI();

		if (ontologyIRI.toString().equals("http://www.semanticweb.org/ontologies/2012/3/CompGuide.owl")) {
			updateIRIs();
			createButton.setEnabled(true);
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			shareButton.setEnabled(true);
		} else {
			createButton.setEnabled(false);
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			shareButton.setEnabled(false);
		}
	}

	private void updateIRIs() {
		Action = factory.getOWLClass(IRI.create(ontologyIRI + "#Action")).getIRI();
		CPG = factory.getOWLClass(IRI.create(ontologyIRI + "#ClinicalPracticeGuideline")).getIRI();
		ClinicalSpeciality = factory.getOWLClass(IRI.create(ontologyIRI + "#ClinicalSpecialty")).getIRI();
		Condition = factory.getOWLClass(IRI.create(ontologyIRI + "#Condition")).getIRI();
		ConditionSet = factory.getOWLClass(IRI.create(ontologyIRI + "#ConditionSet")).getIRI();
		Decision = factory.getOWLClass(IRI.create(ontologyIRI + "#Decision")).getIRI();
		Duration = factory.getOWLClass(IRI.create(ontologyIRI + "#Duration")).getIRI();
		End = factory.getOWLClass(IRI.create(ontologyIRI + "#End")).getIRI();
		Exam = factory.getOWLClass(IRI.create(ontologyIRI + "#Exam")).getIRI();
		GuidelineCategory = factory.getOWLClass(IRI.create(ontologyIRI + "#GuidelineCategory")).getIRI();
		MedicationRecommendation = factory.getOWLClass(IRI.create(ontologyIRI + "#MedicationRecommendation")).getIRI();
		NonMedicationRecommendation = factory.getOWLClass(IRI.create(ontologyIRI + "#NonMedicationRecommendation"))
				.getIRI();
		Option = factory.getOWLClass(IRI.create(ontologyIRI + "#Option")).getIRI();
		Outcome = factory.getOWLClass(IRI.create(ontologyIRI + "#Outcome")).getIRI();
		Parameter = factory.getOWLClass(IRI.create(ontologyIRI + "#Parameter")).getIRI();
		Periodicity = factory.getOWLClass(IRI.create(ontologyIRI + "#Periodicity")).getIRI();
		Plan = factory.getOWLClass(IRI.create(ontologyIRI + "#Plan")).getIRI();
		Precondition = factory.getOWLClass(IRI.create(ontologyIRI + "#PreCondition")).getIRI();
		Procedure = factory.getOWLClass(IRI.create(ontologyIRI + "#Procedure")).getIRI();
		Question = factory.getOWLClass(IRI.create(ontologyIRI + "#Question")).getIRI();
		Scope = factory.getOWLClass(IRI.create(ontologyIRI + "#Scope")).getIRI();
		TriggerCondition = factory.getOWLClass(IRI.create(ontologyIRI + "#TriggerCondition")).getIRI();
		TemporalRestriction = factory.getOWLClass(IRI.create(ontologyIRI + "#TemporalRestriction")).getIRI();
		ClinicalActionType = factory.getOWLClass(IRI.create(ontologyIRI + "#ClinicalActionType")).getIRI();
		Formula = factory.getOWLClass(IRI.create(ontologyIRI + "#Formula")).getIRI();
	}

}
