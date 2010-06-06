package com.evancharlton.mileage.charts;

import android.database.Cursor;

import com.artfulbits.aiCharts.Base.ChartPoint;
import com.artfulbits.aiCharts.Base.ChartPointCollection;
import com.evancharlton.mileage.R;
import com.evancharlton.mileage.dao.Vehicle;

public class MinimumVolumeChart extends VolumeChart {
	@Override
	protected String getAxisTitle() {
		return getString(R.string.stat_min_fuel);
	}

	@Override
	protected void processCursor(LineChartGenerator generator, ChartPointCollection points, Cursor cursor, Vehicle vehicle) {
		int num = 0;
		double minimum_volume = 10000;
		while (cursor.isAfterLast() == false) {
			if (generator.isCancelled()) {
				break;
			}
			double volume = cursor.getDouble(0);
			if (volume < minimum_volume) {
				minimum_volume = volume;
			}
			points.add(new ChartPoint(num, minimum_volume));
			generator.update(num++);
			cursor.moveToNext();
		}
	}
}