import java.util.*;

public class FrequencyHashTable
{
    private  class MapEntry<K,V> implements Map.Entry<K,V>
    {
        private K k; // key
        private V v; // value
        public MapEntry(K key, V value)
        {
            k = key;
            v = value;
        }
        // public methods of the Entry interface
        public K getKey( ) { return k; }
        public V getValue( ) { return v; }
        // utilities not exposed as part of the Entry interface
        public V setValue(V value)
        {
            V old = v;
            v = value;
            return old;
        }
    }
    private class MapEntryList
    {
        private ArrayList<MapEntry<String, Integer>> list = new ArrayList<> ();
        public MapEntryList( )
        {}
        public ArrayList<MapEntry<String, Integer>> getList()
        {
            return list;
        }
    }

    private MapEntryList[] hashTable;
    private int total = 0;
    private int words;

    public FrequencyHashTable() //create a frequency hash table with fixed size 9973
    {hashTable = new MapEntryList[9973];}

    private int hash(String k)
    {return Math.abs(k.hashCode())%9973;}

    public void increment(String k)
    {
        //each time increment total words is added
        total++;
        MapEntry <String,Integer> n = new MapEntry<>(k,1);
        int hashC = hash(k);//hash value of this word
        //if current arraylist is null
        if(hashTable[hashC]==null)
        {
            hashTable[hashC] = new MapEntryList();
            hashTable[hashC].getList().add(n);
            words++;//increment total words this author uses
        }
        else
        {
            for(MapEntry<String, Integer> current:hashTable[hashC].getList())
            {
                if(current.getKey().equals(k))
                {
                    int value = current.getValue();
                    current.setValue(value+1);
                    return;//return if word is found in current arraylist
                }
            }
            //else add new word to arraylist
            hashTable[hashC].getList().add(n);
            words++;
        }
    }
    public double get(String k)
    {
        int hashC = hash(k);
        if(hashTable[hashC]==null)
        {
            return 0.000000001;
        }
        else
        {
            for(MapEntry<String, Integer> current : hashTable[hashC].getList())
            {
                if(current.getKey().equals(k))
                {
                    return current.getValue()/(double)totalCount();
                }
            }
        }
        return 0.000000001;
    }
    public void remove(String k)
    {
        int hashC = hash(k);
        for(int i = 0;i<hashTable[hashC].getList().size();i++)
        {
            if(hashTable[hashC].getList().get(i).getKey().equals(k))//checking current index mapentry
            {
                total -= hashTable[hashC].getList().get(i).getValue();//difference this words used from total words
                hashTable[hashC].getList().remove(i);//remove current index
            }
        }
    }
    private int totalCount()
    {return total;}
    public int size()
    {
        return words;
    }//return how many different words was used
    public boolean isEmpty()//if total words was zero than this hash table is empty
    {return total==0;}

}
