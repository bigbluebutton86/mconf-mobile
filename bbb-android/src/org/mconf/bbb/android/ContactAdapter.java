package org.mconf.bbb.android;

import java.util.List;

import org.mconf.bbb.users.IParticipant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ContactAdapter extends BaseAdapter {
	private Context context;
	ImageView presenter;
	View view;

    private List<IParticipant> listContact;

    public ContactAdapter(Context context, List<IParticipant> listContact) {
        this.context = context;
        this.listContact = listContact;
    }
    
    public void addSection(IParticipant participant) {
    	Contact contact = new Contact(participant);
    	listContact.add(contact);
    }
    
    public void removeSection(IParticipant participant){
    	for (IParticipant c : listContact)
    		if (participant.getUserId() == c.getUserId()) {
    			listContact.remove(c);
    			break;
    		}    			
    }
    
    public void setPresenterStatus(Contact changedStatus)
    {
    	ImageView presenter = (ImageView) view.findViewById(R.id.presenter);
    	if(changedStatus.isPresenter())
    		presenter.setImageDrawable(this.context.getResources().getDrawable(R.drawable.presenter));
    	
    	else
    		presenter.setImageDrawable(this.context.getResources().getDrawable(R.drawable.user));
    	
    }

    public void setStreamStatus( Contact changedStatus)
    {
    	ImageView stream = (ImageView) view.findViewById(R.id.stream);
        if(changedStatus.hasStream())
        	stream.setImageDrawable(this.context.getResources().getDrawable(R.drawable.webcam));
        else
        	stream.setVisibility(ImageView.INVISIBLE);
    }
       
    public void setRaiseHandStatus(Contact changedStatus)
    {
    	 ImageView raiseHand = (ImageView) view.findViewById(R.id.raise_hand);
         if(changedStatus.isRaiseHand())
            raiseHand.setImageDrawable(this.context.getResources().getDrawable(R.drawable.raisehand));
         else
         	raiseHand.setVisibility(ImageView.INVISIBLE);
         	
    }
    
    public int getCount() {
        return listContact.size();
    }

    public Object getItem(int position) {
        return listContact.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Contact entry = (Contact) listContact.get(position);
        
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact, null);
        }
        view = convertView;
        
        TextView contactName = (TextView) convertView.findViewById(R.id.contact_name);
        contactName.setText(entry.getName());
        contactName.setTag(entry.getName());

       
        ImageView moderator = (ImageView) convertView.findViewById(R.id.moderator);
        if(entry.isModerator())
           	moderator.setImageDrawable(this.context.getResources().getDrawable(R.drawable.administrator));
        else
        	moderator.setImageDrawable(this.context.getResources().getDrawable(R.drawable.participant));
        
        setPresenterStatus(entry);
        setStreamStatus(entry);
        setRaiseHandStatus(entry);
        
       
                      

        return convertView;
    }

}