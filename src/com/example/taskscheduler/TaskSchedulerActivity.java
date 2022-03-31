package com.example.taskscheduler;

import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;

public class TaskSchedulerActivity extends Activity {

	EditText edittask;
	DatePicker datePickerStart, datePickerDue;
	TimePicker timePickerStart;
	Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edittask = (EditText) findViewById(R.id.editTask);
		datePickerStart = (DatePicker) findViewById(R.id.datePickerStart);
		datePickerDue = (DatePicker) findViewById(R.id.datePickerDue);
		timePickerStart = (TimePicker) findViewById(R.id.timePickerStarttime);
		btnSave = (Button) findViewById(R.id.btnSave);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void save() {
		validStartDate();
		validDueDate();
	}
	private Boolean validStartDate(){
		Boolean valid = false;
			Date d = new Date();
			Date currentDate = new Date(d.getYear(), d.getMonth(), d.getDate());

			int day = datePickerStart.getDayOfMonth();
			int month = datePickerStart.getMonth();
			int year = datePickerStart.getYear();

			Date startDate = new Date(year - 1900, month, day);
			
			
			
			switch (startDate.compareTo(currentDate)) {
			case -1:
				valid = false;
				Toast.makeText(this, "Start date should not be less than the current date", Toast.LENGTH_SHORT).show();
				break;

			case 0:
				if (!validTime()) {
					valid = false;
					Toast.makeText(this, "Start time should not be less than the current time", Toast.LENGTH_SHORT).show();
				}
				else {
					valid = true;
				}
				break;
				
			case 1:
				valid = true;
				break;
				
			default:
				break;
			}
			return valid;
		}
	private Boolean validDueDate() {
		Boolean valid = false;
		
		int startday = datePickerStart.getDayOfMonth();
		int startmonth = datePickerStart.getMonth();
		int startyear = datePickerStart.getYear();

		Date startDate = new Date(startyear - 1900, startmonth, startday);
		
		int dueday = datePickerDue.getDayOfMonth();
		int duemonth = datePickerDue.getMonth();
		int dueyear = datePickerDue.getYear();

		Date dueDate = new Date(dueyear - 1900, duemonth, dueday);
		
		switch (dueDate.compareTo(startDate)) {
		case -1:
			valid = false;
			Toast.makeText(this, "Due date should not be less than the start date", Toast.LENGTH_SHORT).show();
			break;

		case 0:
			valid = false;
			Toast.makeText(this, "Due date should not be less than the start date", Toast.LENGTH_SHORT).show();
			break;
			
		case 1:
			valid = true;
			break;
			
		default:
			break;
		}
		
		return valid;
	}
	
	
	private Boolean validTime(){
		Date date = new Date();
		int currentHour = date.getHours();
		int currentMin = date.getMinutes();
		
		int startHour = timePickerStart.getCurrentHour();
		int startMin = timePickerStart.getCurrentMinute();
		
		if (startHour < currentHour){
			return false;
		}
		
		else if (startHour == currentHour && startMin <= currentMin){
			return false;
		}
		else{
		return true;
		}
	}
}
