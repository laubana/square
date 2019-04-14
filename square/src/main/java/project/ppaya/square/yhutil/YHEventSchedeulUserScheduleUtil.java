package project.ppaya.square.yhutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import project.ppaya.square.vo.*;

public class YHEventSchedeulUserScheduleUtil
{	
	public static boolean isExistPeriodInEventScheduleUserScheduleList(ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list, long start_date, long end_date)
	{
		for(int i = 0; i < event_schedule_user_schedule_list.size(); i++)
		{
			if(
					(start_date < event_schedule_user_schedule_list.get(i).getEnd_date() && event_schedule_user_schedule_list.get(i).getEnd_date() <= end_date) ||
					(start_date <= event_schedule_user_schedule_list.get(i).getStart_date() && event_schedule_user_schedule_list.get(i).getStart_date() < end_date) ||
					(event_schedule_user_schedule_list.get(i).getStart_date() < start_date && end_date < event_schedule_user_schedule_list.get(i).getEnd_date())
					)
			{
				return true;
			}
		}
		
		return false;
	}
	public static ArrayList<EventScheduleUserSchedule> cropEventScheduleUserScheduleList(ArrayList<EventScheduleUserSchedule> old_event_schedule_user_schedule_list, long start_date, long end_date)
	{
		ArrayList<EventScheduleUserSchedule> new_event_schedule_user_schedule_list = (ArrayList<EventScheduleUserSchedule>)old_event_schedule_user_schedule_list.clone();

		for(int i = 0; i < new_event_schedule_user_schedule_list.size(); i++)
		{			
			if(new_event_schedule_user_schedule_list.get(i).getStart_date() < start_date)
			{
				new_event_schedule_user_schedule_list.get(i).setStart_date(start_date);
			}
			if(end_date < new_event_schedule_user_schedule_list.get(i).getEnd_date())
			{
				new_event_schedule_user_schedule_list.get(i).setEnd_date(end_date);
			}
		}
		
		return new_event_schedule_user_schedule_list;
	}
	public static ArrayList<EventScheduleUserSchedule> parseIntegratedEventScheduleUserScheduleListToView
	(
			ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list,
			String user_id,
			int event_schedule_id,
			long start_date,
			long end_date
			)
	{
		ArrayList<EventScheduleUserSchedule> view = new ArrayList<>();
		
		if(event_schedule_user_schedule_list.size() != 0)
		{
			if(start_date < event_schedule_user_schedule_list.get(0).getStart_date())
			{
				view.add(new EventScheduleUserSchedule(user_id, event_schedule_id, start_date, event_schedule_user_schedule_list.get(0).getStart_date(), 0));
			}
			
			for(int i = 0; i < event_schedule_user_schedule_list.size() - 1; i++)
			{
				view.add(event_schedule_user_schedule_list.get(i));
				
				view.add(new EventScheduleUserSchedule(user_id, event_schedule_id, event_schedule_user_schedule_list.get(i).getEnd_date(), event_schedule_user_schedule_list.get(i + 1).getStart_date(), 0));
			}
			view.add(event_schedule_user_schedule_list.get(event_schedule_user_schedule_list.size() - 1));
			
			if(event_schedule_user_schedule_list.get(event_schedule_user_schedule_list.size() - 1).getEnd_date() < end_date)
			{
				view.add(new EventScheduleUserSchedule(user_id, event_schedule_id, event_schedule_user_schedule_list.get(event_schedule_user_schedule_list.size() - 1).getEnd_date(), end_date, 0));
			}
		}
		else
		{
			view.add(new EventScheduleUserSchedule(user_id, event_schedule_id, start_date, end_date, 0));
		}
		return view;
	}
	public static ArrayList<EventScheduleUserSchedule> integrateEventScheduleUserScheduleListList(ArrayList<ArrayList<EventScheduleUserSchedule>> event_schedule_user_schedule_list_list, int event_schedule_id)
	{
		ArrayList<EventScheduleUserSchedule> temp_event_schedule_user_schedule_list = new ArrayList<>();
		ArrayList<EventScheduleUserSchedule> integrated_event_schedule_user_schedule_list = new ArrayList<>();
		
		for(int i = 0; i < event_schedule_user_schedule_list_list.size(); i++)
		{
			for(int j = 0; j < event_schedule_user_schedule_list_list.get(i).size(); j++)
			{
				temp_event_schedule_user_schedule_list.add(event_schedule_user_schedule_list_list.get(i).get(j));
			}
		}
		
		return integrateEventScheduleUserScheduleList(temp_event_schedule_user_schedule_list, "", event_schedule_id);
	}
	public static ArrayList<EventScheduleUserSchedule> integrateEventScheduleUserScheduleList
	(
			ArrayList<EventScheduleUserSchedule> old_event_schedule_user_schedule_list,
			String user_id,
			int event_schedule_id
			)
	{
		ArrayList<EventScheduleUserSchedule> temp_event_schedule_user_schedule_list = (ArrayList<EventScheduleUserSchedule>)old_event_schedule_user_schedule_list.clone();
		ArrayList<EventScheduleUserSchedule> new_event_schedule_user_schedule_list = new ArrayList<>();  
		long start_date = -1;
		long end_date = -1;
		
		Collections.sort(temp_event_schedule_user_schedule_list, new Comparator<EventScheduleUserSchedule>()
		{
			public int compare(EventScheduleUserSchedule event_schedule_user_schedule1, EventScheduleUserSchedule event_schedule_user_schedule2)
			{
				return Long.compare(event_schedule_user_schedule1.getStart_date(), event_schedule_user_schedule2.getStart_date());
			}
		});
		
		for(int i = 0; i < temp_event_schedule_user_schedule_list.size(); i++)
		{
			if(start_date == -1 || end_date == -1)
			{
				start_date = temp_event_schedule_user_schedule_list.get(i).getStart_date();
				end_date = temp_event_schedule_user_schedule_list.get(i).getEnd_date();
			}
			
			try
			{
				if(temp_event_schedule_user_schedule_list.get(i + 1).getStart_date() <= end_date)
				{
					if(end_date < temp_event_schedule_user_schedule_list.get(i + 1).getEnd_date())
					{
						end_date = temp_event_schedule_user_schedule_list.get(i + 1).getEnd_date();
					}
					else{continue;}
				}
				else
				{					
					new_event_schedule_user_schedule_list.add(new EventScheduleUserSchedule(user_id, event_schedule_id, start_date, end_date, 1));
					
					start_date = -1;
					end_date = -1;
					
					continue;
				}
			}
			catch(Exception error)
			{				
				new_event_schedule_user_schedule_list.add(new EventScheduleUserSchedule(user_id, event_schedule_id, start_date, end_date, 1));
				
				break;
			}
		}
		
		return new_event_schedule_user_schedule_list;
	}
}
