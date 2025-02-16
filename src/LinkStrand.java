import java.lang.StringBuilder;

public class LinkStrand implements IDnaStrand{
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    private class Node {
        String info;
        Node next; 
        Node(String x) {
            info = x;
        }
        Node(String x, Node n) {
            info = x;
            next = n;
        }
    }

    public LinkStrand(){
		this("");
	}

    public LinkStrand(String dna) {
        initialize(dna);
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = myFirst.info.length();
        myIndex = 0;
        myLocalIndex = 0;
        myAppends = 0;
        myCurrent = myFirst;
    }

    @Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna);
        myLast.next = newNode;
        myLast = myLast.next;
        myAppends += 1;
        mySize += dna.length();
        return this;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public String toString() {
        StringBuilder myStrings = new StringBuilder();
        Node current = myFirst;
        while (current != null) {
            myStrings.append(current.info);
            current = current.next;
        }
        return myStrings.toString();
    }
    /* 
    @Override
    public IDnaStrand reverse() {
        LinkStrand newStrand = new LinkStrand();
        Node current = myFirst; 
        while (current != null) {
            StringBuilder reverse = new StringBuilder(current.info);
            reverse.reverse();
            String s = reverse.toString();
            Node newNode = new Node(s, newStrand.myFirst);
            newStrand.myFirst = newNode; 
            newStrand.mySize += s.length();
            newStrand.myAppends ++;
            current = current.next;
        }
        return newStrand;
    }
    /* 
    @Override 
    public char charAt(int index) {
        if(index<0 || index>=mySize){
            throw new IndexOutOfBoundsException();
        }
        Node n = myFirst;
        int totalIndicies = 0;
        while (n != null) {
            int currTotal = totalIndicies;
            totalIndicies += n.info.length();
            n = n.next;
            if (totalIndicies >= index) {
                myLocalIndex = index - currTotal;
                break;
            }
            n = n.next;
        }
        myCurrent = n;
        //String myString = myCurrent.info;
        myIndex = index; 
        return myCurrent.info.charAt(myLocalIndex);
    }
    */
    @Override
    public char charAt(int index) {
        if(index<0 || index>=this.size()){
            throw new IndexOutOfBoundsException();
        }
        if(index < myIndex){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while(index > myIndex && myCurrent!=null){
            myIndex++; 
            myLocalIndex++;
            if(myCurrent.next != null && myCurrent.info.length()<=myLocalIndex){
                myCurrent = myCurrent.next;
                myLocalIndex = 0;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }
    @Override
    public IDnaStrand reverse() {
        LinkStrand strand = new LinkStrand();
        int current = myAppends;
        while(strand.size()<size()){
            Node n = myFirst;
            int i = 0;
            while(i<current){
                n = n.next;
                i++;
            }
            StringBuilder s = new StringBuilder(n.info);
            s.reverse();
            strand.append(s.toString());
            strand.myAppends--;
            current--;
        }
        return strand;
    }
}
