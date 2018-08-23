package userinterface;

import javax.swing.*;
import core.*;
import java.awt.*;
import java.net.URL;

class CardUi {
    private Card card;
    private ImageIcon imageIcon;
    private JButton button;
    private JLabel label;
    private int position;

    public CardUi(Card card, JButton button) {
        this.card = card;
        this.button = button;

        selectFrontImage(button);
    }

    public CardUi(Card card, JLabel label) {
        this.card = card;
        this.label = label;

        selectFrontImage(label);
    }

    public CardUi(Card card, JLabel label, int pos) {
        this.card = card;
        this.label = label;
        this.position = pos;

        if(position == 2)
            selectHorizontalBackImage();
            //selectFrontImage(label);
        else
            selectVerticalBackImage();
            //selectFrontImage(label);
    }

    public CardUi(JLabel label) {
        this.label = label;

        //selectFrontImage(label);
    }

    private void selectFrontImage(JComponent comp) {
        String filePath = "../images/";

        switch(card.getFaceValue()){
            case ACE:
                filePath = filePath + "Ace";
                break;
            case KING:
                filePath = filePath + "King";
                break;
            case QUEEN:
                filePath = filePath + "Queen";
                break;
            case JACK:
                filePath = filePath + "Jack";
                break;
            case TEN:
                filePath = filePath + "Ten";
                break;
            case NINE:
                filePath = filePath + "Nine";
                break;
        }

        filePath += "_";

        switch(card.getSuit()) {
            case HEARTS:
                filePath = filePath + "Hearts";
                break;
            case SPADES:
                filePath = filePath + "Spades";
                break;
            case DIAMONDS:
                filePath = filePath + "Diamonds";
                break;
            case CLUBS:
                filePath = filePath + "Clubs";
                break;
        }

        filePath = filePath + ".jpg";


        try {
            URL path = getClass().getResource(filePath);
            imageIcon = new ImageIcon(path);

            if(comp instanceof JButton) {
                imageIcon = imageResizeHorizontal(imageIcon, button);
                button = new JButton(imageIcon);
            }
            else {
                imageIcon = imageResizeTrump(imageIcon);
                label = new JLabel(imageIcon);
            }
        }
        catch (NullPointerException e) {
            System.err.println("Resource not found. Error: " + e + "\n" + filePath );
            imageIcon = null;
        }
    }

    private void selectVerticalBackImage() {
        String filePath = "../images/back_Vertical.jpg";

        try {
            URL path = getClass().getResource(filePath);

            imageIcon = new ImageIcon(path);
            imageIcon = imageResizeVertical(imageIcon);

            label = new JLabel(imageIcon);
        }
        catch (Exception e) {
            System.err.println("Resource not found. Error: " + e);
            imageIcon = null;
        }
    }

    private void selectHorizontalBackImage () {
        String filePath = "../images/back_Horizontal.jpg";

        try {
            URL path = getClass().getResource(filePath);

            imageIcon = new ImageIcon(path);
            imageIcon = imageResizeHorizontal(imageIcon, label);

            label = new JLabel(imageIcon);
        }
        catch (Exception e) {
            System.err.println("Resource not found. Error: " + e);
            imageIcon = null;
        }
    }

    //  Images Resizers
    private ImageIcon imageResizeVertical(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    private ImageIcon imageResizeHorizontal(ImageIcon icon, JComponent comp) {
        Image image = icon.getImage();
        Image newImage;

        if(comp instanceof JButton) {
            newImage = image.getScaledInstance(115, 150, Image.SCALE_SMOOTH);
        }
        else {
            newImage = image.getScaledInstance(115, 150, Image.SCALE_SMOOTH);
        }

        icon = new ImageIcon(newImage);
        return icon;
    }

    private ImageIcon imageResizeTrump(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(114, 149, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    //  GETTERS AND SETTERS
    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
