package com.cardsui.pattern;

import android.view.View;

import com.fima.cardsui.objects.Card;
import com.fima.cardsui.objects.CardStack;

/**
 * Created by b608 on 14-3-10.
 */
public class CardUtils {
    private static CardUtils instance;
    private CardUtils() {
        // not allow to instance it outside.
    }
    public static CardUtils get() {
        if (null == instance) {
            instance = new CardUtils();
        }
        return instance;
    }

    public CardStack getCardStack(final String title) {
        CardStack stack = new CardStack();
        stack.setTitle(title);
        return stack;
    }

    public Card getCard(final String title) {
        return getCard(title, null);
    }

    public Card getCard(final String title, final View.OnClickListener clicker) {
        return getCard(title, clicker, null);
    }
    public Card getCard(final String title, final View.OnClickListener clicker,
                        final View.OnLongClickListener longClicker) {
        Card card = new MyCard(title);
        if (null != clicker) {
            card.setOnClickListener(clicker);
        }
        if (null != longClicker) {
            card.setOnLongClickListener(longClicker);
        }
        return card;
    }

    public Card getCard(String title, String description, String color, String titleColor,
                        Boolean hasOverflow, Boolean isClickable) {
        return new MyPlayCard(title, description, color, titleColor, hasOverflow, isClickable);
    }
}
