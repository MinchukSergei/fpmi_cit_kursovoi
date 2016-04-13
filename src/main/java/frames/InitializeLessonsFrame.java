package frames;

import entities.lessons.ClLessonCIT;
import entities.lessons.ClLessonFPMI;
import entities.lessons.ExportClLesson;
import entities.subjects.ClSubjectCIT;
import entities.subjects.ClSubjectFPMI;
import entities.subjects.ExportClSubject;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeLessonsFrame extends InitFrame {
    public InitializeLessonsFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeLessonsFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClLessonFPMI.GET_ALL_LESSON_FPMI,
                    ClLessonCIT.GET_ALL_LESSON_CIT_PROC, ExportClLesson.GET_ALL_ID, ClLessonCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClLessonCIT> model =
                        (DefaultListModel<ClLessonCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClLessonCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClLesson.class);
        });
    }
}
