package com.callfornation.savetheirsouls.register.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.callfornation.savetheirsouls.R;
import com.callfornation.savetheirsouls.databinding.ListItemFamilyMemberBinding;

import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<FamilyMember> familyMembers = new ArrayList<>();
    public FamilyAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemFamilyMemberBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item_family_member,parent, false);
        return new FamilyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FamilyViewHolder familyViewHolder = (FamilyViewHolder) holder;
        familyViewHolder.bindView(familyMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return familyMembers.size();
    }

    private class FamilyViewHolder extends RecyclerView.ViewHolder {
        private ListItemFamilyMemberBinding binding;
        FamilyViewHolder(ListItemFamilyMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindView(FamilyMember familyMember) {
            binding.setFamilyMember(familyMember);
        }
    }

    public void addFamilyMember(FamilyMember familyMember) {
        familyMembers.add(familyMember);
        notifyDataSetChanged();
    }
}
