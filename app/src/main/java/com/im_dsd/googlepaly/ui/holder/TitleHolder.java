package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.CategoryBean;
import com.im_dsd.googlepaly.utils.UIUtils;


/**
 * 分类页标题栏holder
 * 
 * @author Kevin
 */
public class TitleHolder extends BaseHolder<CategoryBean> {

	private TextView tvTitle;


	@Override
	public View setItemView() {
		View view = View.inflate(UIUtils.getContext(),
				R.layout.list_item_title, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView(CategoryBean data) {
		tvTitle.setText(data.getTitle());
	}
}
