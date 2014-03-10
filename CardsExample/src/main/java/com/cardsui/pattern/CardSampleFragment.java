package com.cardsui.pattern;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fima.cardsui.objects.Card;
import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;

public class CardSampleFragment extends Fragment {

    private CardUI mCardView;

    private Resources mResource;
    private String[] getStringArray(int id) {
        return mResource.getStringArray(id);
    }
    private String[] getStringArray(String resName) {
        int id = mResource.getIdentifier(resName, "array", getActivity().getPackageName());
        if (id > 0) {
            return getStringArray(id);
        } else {
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState);
//        setContentView(R.layout.activity_main);
        View view = inflater.inflate(R.layout.fragment_cards, null);

        // init CardView
        mCardView = (CardUI) view.findViewById(R.id.cardsview);
        mCardView.setSwipeable(false);

        mResource = getResources();

//        addRegularCardStack();
//        addPlayCardStack();
        addPatternCardStacks();

        // draw cards
        mCardView.refresh();
        return view;
    }

    private void addPatternCardStacks() {
        String[] partTitles = getStringArray(R.array.pattern_functional_part);
        int index = 0;
        for (String part : partTitles) {
            initStackAndAppendCards(part, index++);
        }
    }

    private static final String PATTERN_TITLE_PREFIX = "title_part_";
    private void initStackAndAppendCards(String title, int index) {
        mCardView.addStack(CardUtils.get().getCardStack(title));

        String[] captions = getStringArray(PATTERN_TITLE_PREFIX + index);
        if (null != captions) {
            for (String name : captions) {
                mCardView.addCardToLastStack(CardUtils.get().getCard(name));
            }
        }
    }

    private void addPlayCardStack() {
        CardStack stackPlay = CardUtils.get().getCardStack("GOOGLE PLAY CARDS");
        mCardView.addStack(stackPlay);

        // add one card, and then add another one to the last stack.
        mCardView.addCard(CardUtils.get().getCard("Google Play Cards"));
        mCardView.addCardToLastStack(CardUtils.get().getCard("By Androguide & GadgetCheck"));

        mCardView.addCardToLastStack(CardUtils.get().getCard("Google Play",
                "This card mimics the new Google play cards look", "#33b6ea",
                "#33b6ea", true, false));

        mCardView.addCardToLastStack(CardUtils.get().getCard(
                "Menu Overflow",
                "The PlayCards allow you to easily set a menu overflow on your card.\nYou can also declare the left stripe's color in a String, like \"#33B5E5\" for the holo blue color, same for the title color.",
                "#e00707", "#e00707", false, true));

        // add one card
        mCardView.addCard(CardUtils.get().getCard(
                "Different Colors for Title & Stripe",
                "You can set any color for the title and any other color for the left stripe",
                "#f2a400", "#9d36d0", false, false));

        mCardView.addCardToLastStack(CardUtils.get().getCard(
                "Set Clickable or Not",
                "You can easily implement an onClickListener on any card, but the last boolean parameter of the PlayCards allow you to toggle the clickable background.",
                "#4ac925", "#222222", true, true));
    }

    private void addRegularCardStack() {
        CardStack stack2 = CardUtils.get().getCardStack("REGULAR CARDS");
        mCardView.addStack(stack2);

        // add AndroidViews Cards
        mCardView.addCard(CardUtils.get().getCard("Get the CardsUI view"));
        mCardView.addCardToLastStack(CardUtils.get().getCard("for Android at"));
        Card androidViewsCard = CardUtils.get().getCard("www.androidviews.net"
                , new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.androidviews.net/"));
                        startActivity(intent);

                    }
                },
                new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(v.getContext(), "This is a long click", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                });
        mCardView.addCardToLastStack(androidViewsCard);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_main, menu);
    }
}
