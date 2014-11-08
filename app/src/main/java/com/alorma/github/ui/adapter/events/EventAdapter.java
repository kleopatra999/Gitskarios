package com.alorma.github.ui.adapter.events;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.events.EventType;
import com.alorma.github.ui.adapter.LazyAdapter;
import com.alorma.github.ui.adapter.events.views.CreatedEventView;
import com.alorma.github.ui.adapter.events.views.ForkEventView;
import com.alorma.github.ui.adapter.events.views.GithubEventView;
import com.alorma.github.ui.adapter.events.views.IssueCommentEventView;
import com.alorma.github.ui.adapter.events.views.PushEventView;
import com.alorma.github.ui.adapter.events.views.UnhandledEventView;
import com.alorma.github.ui.adapter.events.views.WatchEventView;

import java.util.ArrayList;

/**
 * Created by Bernat on 03/10/2014.
 */
public class EventAdapter extends LazyAdapter<GithubEvent> {

	public EventAdapter(Context context, ArrayList<GithubEvent> githubEvents) {
		super(context, githubEvents);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GithubEvent githubEvent = getItem(position);

		View v = inflate(githubEvent);

		return v;
	}

	public View inflate(GithubEvent event) {
		GithubEventView v;

		if (event.getType() == EventType.PushEvent) {
			v = new PushEventView(getContext());
		} else if (event.getType() == EventType.WatchEvent) {
			v = new WatchEventView(getContext());
		}  else if (event.getType() == EventType.CreateEvent) {
			v = new CreatedEventView(getContext());
		}  else if (event.getType() == EventType.IssueCommentEvent) {
			v = new IssueCommentEventView(getContext());
		}   else if (event.getType() == EventType.ForkEvent) {
			v = new ForkEventView(getContext());
		} else {
			v = new UnhandledEventView(getContext());
		}

		v.setEvent(event);

		return v;
	}
}
