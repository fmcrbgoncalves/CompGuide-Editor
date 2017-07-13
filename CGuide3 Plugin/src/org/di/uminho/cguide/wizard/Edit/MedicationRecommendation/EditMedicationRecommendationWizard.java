package org.di.uminho.cguide.wizard.Edit.MedicationRecommendation;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditMedicationRecommendationWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditMedicationRecommendationWizard.class);

	public EditMedicationRecommendationPage editmedicationrecommendationpage;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditMedicationRecommendationWizard(OWLEditorKit editorKit, String procedure_individual) {
		setTitle("Medication Recommendation Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditMedicationRecommendationPage.ID,
				editmedicationrecommendationpage = new EditMedicationRecommendationPage(editorKit,
						procedure_individual));

		setCurrentPanel(EditMedicationRecommendationPage.ID);
	}

}
