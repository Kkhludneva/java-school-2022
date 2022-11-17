package ru.croc.task5.annotations;

public class AnnotatedImage {
    private final String imagePath;

    private final Annotation[] annotations;


    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y){
        for (Annotation an: annotations) {
            if (an.getFigure().isPointInside(x,y)==true){
                return an;
            }
        }
        return null;
    }

    public Annotation findByLabel(String label) {
        for (Annotation an: this.annotations){
            if(an.getCaption().contains(label) == true){
                return an;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String rslt = "";
        for (Annotation an: annotations) {
            rslt+= an.toString()+"\n";
        }
        return rslt;
    }
}

