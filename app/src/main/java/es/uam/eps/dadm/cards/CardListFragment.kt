package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import es.uam.eps.dadm.cards.databinding.FragmentCardListBinding

private val TAG: String = "CardListFragment"

class CardListFragment : Fragment() {
    private lateinit var binding: FragmentCardListBinding
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_card_list,
                container,
                false
        )
        adapter = CardAdapter()
        val args = CardListFragmentArgs.fromBundle(arguments!!)

        val deck = CardsApplication.getDeck(args.deckId)
        adapter.data = deck.cards
        adapter.deckId=deck.id
        binding.cardRecyclerView.adapter = adapter

        binding.newCardFab.setOnClickListener {
            val card = Card("", "")
            CardsApplication.addCard(card,deck.id)
            it.findNavController().navigate(CardListFragmentDirections
                    .actionCardListFragmentToCardEditFragment(card.id,deck.id))
        }

        return binding.root
    }
}