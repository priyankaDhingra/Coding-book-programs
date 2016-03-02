package me.pd.test.codeint;

import java.util.LinkedList;
import java.util.ListIterator;

import me.pd.test.intrface.PriorityQueue;
import me.pd.test.intrface.QueueItem;

class QItm implements QueueItem {
	int priority = 0;

	public QItm(int priority) {
		super();
		if (priority > 99) {
			System.out.println("PriorityError");
		} else {
			this.priority = priority;
		}
	}

	@Override
	public int priority() {

		return priority;
	}

}

public class QuickNextQueue implements PriorityQueue {

	LinkedList priorityQ = new LinkedList();

	@Override
	public void queue(QueueItem q) {
		if (priorityQ.isEmpty()) {
			priorityQ.add(q);
		} else {
			QItm temp = (QItm) q;
			int index = 0;
			for(Object qitm:priorityQ){
				QItm element = (QItm)qitm;
				if (element.priority > temp.priority) {
					priorityQ.add(index, temp);
					return;
				}
				index++;
			}
			
			priorityQ.add(temp);
		}
	}

	@Override
	public QueueItem dequeue() {
		if (!priorityQ.isEmpty()) {
			QItm hold = (QItm) priorityQ.get(priorityQ.size() - 1);

			priorityQ.remove(priorityQ.size() - 1); 
			return hold;
		}
		return null;
	}

}