package com.alorma.github.ui.adapter.detail.repo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alorma.github.R;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.dto.response.ContentType;
import com.alorma.githubicons.GithubIconDrawable;
import com.alorma.githubicons.GithubIconify;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class RepoContentAdapter extends ArrayAdapter<Content> {

	private final LayoutInflater inflater;
	private Context context;

	public RepoContentAdapter(Context context, List<Content> objects) {
		super(context, 0, objects);
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = inflater.inflate(R.layout.row_content, parent, false);

		TextView textName = (TextView) v.findViewById(R.id.name);
		ImageView image = (ImageView) v.findViewById(R.id.icon);

		Content item = getItem(position);

		textName.setText(item.name);

		GithubIconDrawable iconDrawable = null;
		if (ContentType.dir.equals(item.type)) {
			iconDrawable = new GithubIconDrawable(context, GithubIconify.IconValue.octicon_file_directory);
		} else if (ContentType.submodule.equals(item.type)) {
			iconDrawable = new GithubIconDrawable(context, GithubIconify.IconValue.octicon_file_submodule);
		} else if (ContentType.file.equals(item.type)) {
			iconDrawable = new GithubIconDrawable(context, GithubIconify.IconValue.octicon_file_text);
		} else if (ContentType.up.equals(item.type)) {
			iconDrawable = new GithubIconDrawable(context, GithubIconify.IconValue.octicon_arrow_left);
		}

		if (iconDrawable != null) {
			iconDrawable.sizeDp(32);
			iconDrawable.colorRes(R.color.gray_github_medium);

			image.setImageDrawable(iconDrawable);
		}

		return v;
	}
}
