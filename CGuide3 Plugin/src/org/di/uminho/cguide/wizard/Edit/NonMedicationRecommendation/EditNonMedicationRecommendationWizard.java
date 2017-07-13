package org.di.uminho.cguide.wizard.Edit.NonMedicationRecommendation;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditNonMedicationRecommendationWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditNonMedicationRecommendationWizard.class);

	public EditNonMedicationRecommendationPage editnonmedicationrecommendationpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditNonMedicationRecommendationWizard(OWLEditorKit editorKit, String procedure_individual) {
		setTitle("Medication Recommendation Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditNonMedicationRecommendationPage.ID,
				editnonmedicationrecommendationpage = new EditNonMedicationRecommendationPage(editorKit,
						procedure_individual));

		setCurrentPanel(EditNonMedicationRecommendationPage.ID);
	}

}
