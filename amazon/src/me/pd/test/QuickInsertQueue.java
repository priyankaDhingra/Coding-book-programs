package me.pd.test;

import java.util.LinkedList;
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

public class QuickInsertQueue implements PriorityQueue {

	LinkedList priorityQ = new LinkedList();

	@Override
	public void queue(QueueItem q) {
		priorityQ.add(q); // stored, but not correct location
	}

	@Override
	public QueueItem dequeue() {
		if(priorityQ.isEmpty()){
			return null;
		}
		
		QItm max= (QItm) priorityQ.get(0);
		int ptr=0,maxPtr=0;
		for(Object qitm:priorityQ){
			
			QItm element = (QItm)qitm;
			if (element.priority > max.priority) {
				max=element;
				maxPtr=ptr;
			}
			ptr++;
		}
		priorityQ.remove(maxPtr);
		return max;
	}
	
	
}

//public void print() {
//	ListIterator<QItm> listIterator = priorityQ.listIterator();
//	while (listIterator.hasNext()) {
//		System.out.println(listIterator.next().priority);
//	}
//
//}