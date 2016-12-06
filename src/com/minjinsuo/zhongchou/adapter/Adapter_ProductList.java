package com.minjinsuo.zhongchou.adapter;

import java.util.ArrayList;
import java.util.List;

import net.zkbc.p2p.fep.message.protocol.GetHotChoosePrjListResponse.ElementHotPrjList;

import org.xutils.x;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.minjinsuo.zhongchou.R;
import com.minjinsuo.zhongchou.utils.StatusUtils;
import com.minjinsuo.zhongchou.utils.StringUtils;

/**
 * 首页列表数据适配器
 */
public class Adapter_ProductList extends BaseAdapter {

	public Context context;
	private ArrayList<ElementHotPrjList> tenderList;
	private ImageOptions options;

	public Adapter_ProductList(Context context) {
		super();
		this.context = context;
		// 初始化
		tenderList = new ArrayList<ElementHotPrjList>();

		options = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(0))
				.setIgnoreGif(false)
				.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
				.setLoadingDrawableId(R.drawable.loading)
				.setFailureDrawableId(R.drawable.icon_faild).build();
	}

	public void setData(List<ElementHotPrjList> list) {
		if (list != null) {
			this.tenderList.addAll(list);
		}
	}

	public void deleteData() {
		if (this.tenderList != null && this.tenderList.size() > 0) {
			this.tenderList.clear();
		}
	}

	public ArrayList<ElementHotPrjList> getData() {
		return tenderList;
	}

	@Override
	public int getCount() {
		if (tenderList != null) {
			return tenderList.size();
		} else {
			return 0;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_home, null);
			holder.iv_left_icon = (ImageView) convertView
					.findViewById(R.id.iv_left_icon);
			holder.title = (TextView) convertView.findViewById(R.id.title_home);
			holder.amount = (TextView) convertView.findViewById(R.id.amount);
			holder.tv_status = (TextView) convertView
					.findViewById(R.id.tv_status);
			holder.tv_support_num = (TextView) convertView
					.findViewById(R.id.tv_support_num);
			holder.tv_day_haveleft = (TextView) convertView
					.findViewById(R.id.tv_day_haveleft);
			holder.tv_progress = (TextView) convertView
					.findViewById(R.id.tv_progress);
			holder.horizontal_pb = (ProgressBar) convertView
					.findViewById(R.id.horizontal_pb);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ElementHotPrjList info = tenderList.get(position);
		if (info != null) {
			x.image().bind(holder.iv_left_icon, info.getImgAddr(), options);
			holder.title.setText(info.getNam());
			// 已支持人数
			holder.tv_support_num.setText(info.getSuppedCnt() + "人");
			// 已支持金额
			holder.amount.setText(StringUtils.exceedW(info.getSuppedAmt4Succ()
					+ "元"));
			holder.tv_day_haveleft.setText(info.getPrjEndSurplusDays() + "天");
			// 投资进度
			double progress = Double.parseDouble(info.getSuppedAmt4Succ())
					/ Double.parseDouble(info.getPrjAmount());

			holder.horizontal_pb.setProgress((int) (progress * 100));
			holder.tv_progress.setText((progress * 100) + "%");

			// 项目状态
			String code_status = info.getStatus();
			holder.tv_status.setText(StatusUtils.getStatusByCode(code_status));

		}

		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return tenderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView title, amount, tv_status, tv_support_num,
				tv_day_haveleft, tv_progress;
		private ImageView iv_left_icon;
		public ProgressBar horizontal_pb;
	}

}
