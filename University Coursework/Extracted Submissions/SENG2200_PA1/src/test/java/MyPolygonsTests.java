import c3256730.seng2200.pa1.MyPolygons;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Created by Jordan on 12-Mar-17.
 */
public class MyPolygonsTests {
    @Test
    public void Size_EmptyList_SizeZero()
    {
        MyPolygons list = new MyPolygons();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void prepend_EmptyList_AddSuccessfullyToHead()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message");
        Assert.assertEquals(0,list.indexOf("Message"));
    }

    @Test
    public void prepend_PopulatedList_AddSuccessfullyToHead()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        Assert.assertEquals(0,list.indexOf("Message3"));
    }

    @Test
    public void append_EmptyList_AddSuccessfullyToTail()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message");
        Assert.assertEquals(0,list.indexOf("Message"));
    }

    @Test
    public void append_PopulatedList_AddSuccessfullyToTail()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        Assert.assertEquals(2,list.indexOf("Message3"));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void AddAt_EmptyList__InvalidIndex_ThrowException()
    {
        MyPolygons list = new MyPolygons();
        list.insert("Message", 2);
    }

    @Test
    public void AddAt_EmptyList_PositionZero_AddToPositionZero()
    {
        MyPolygons list = new MyPolygons();
        list.insert("Message",0);
        //It can "add" at position zero fine since there are no nodes but the
        //Problem with this is that this method declaration HAS to insert the node before the index
        //So inserting at position 0 will place the node it at position '-1'
        //Soooo should we mention to the discussion board about the method declaration?

        //Fixed by updating insert method
        Assert.assertEquals(0,list.indexOf("Message"));
    }
    
    @Test
    public void AddAt_PopulatedList_PositionZero_AddToPositionZero()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.insert("NewMessage",0);
        Assert.assertEquals("NewMessage", list.getDataAtIndex(0));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void AddAt_PopulatedList_InvalidIndex_ThrowException()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        list.insert("NewMessage",4);
    }

    @Test
    public void AddAt_PopulatedList_ValidIndex_AddsAtPositionBeforeIndexVariable()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        list.insert("NewMessage", 2);
        Assert.assertEquals(2, list.indexOf("NewMessage"));
    }

    @Test
    public void AddAt_PopulatedListWith5Elements_AddAt2_ExpectElementAddedAt2_TestThatElementIsOnlyAddedOnce()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        list.append("Message4");
        list.append("Message5");
        Assert.assertEquals(5,list.size());

        list.insert("NewMessage", 2);
        Assert.assertEquals(6, list.size());

        Assert.assertEquals("Message1",list.getDataAtIndex(0));
        Assert.assertEquals("Message2",list.getDataAtIndex(1));
        Assert.assertEquals("NewMessage",list.getDataAtIndex(2));
        Assert.assertEquals("Message3",list.getDataAtIndex(3));
        Assert.assertEquals("Message4",list.getDataAtIndex(4));
        Assert.assertEquals("Message5",list.getDataAtIndex(5));
    }

    @Test
    public void IndexOf_EmptyList_MissingObject_ReturnNegativeOne()
    {
        MyPolygons list = new MyPolygons();
        Assert.assertEquals(-1, list.indexOf("Message"));
    }

    @Test
    public void IndexOf_PopulatedList_MissingObject_ReturnNegativeOne()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        Assert.assertEquals(-1, list.indexOf("SpecificMessage"));
    }

    @Test
    public void IndexOf_EmptyList_ReturnZero()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message");
        Assert.assertEquals(0,list.indexOf("Message"));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getDataAtIndex_EmptyList_ThrowException()
    {
        MyPolygons list = new MyPolygons();
        list.getDataAtIndex(3);
    }

    @Test
    public void getDataAtIndex_PopulatedList_ReturnData()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        Assert.assertEquals("Message3",list.getDataAtIndex(2));
    }

    @Test(expected=NoSuchElementException.class)
    public void RemoveFromHead_EmptyList_ThrowException()
    {
        MyPolygons list = new MyPolygons();
        list.removeFromHead();
    }

    @Test
    public void RemoveFromHead_PopulatedList_RemoveFirstNode()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        list.removeFromHead();
        Assert.assertEquals(0,list.indexOf("Message2"));
    }

    @Test(expected=NoSuchElementException.class)
    public void RemoveFromTail_EmptyList_ThrowException()
    {
        MyPolygons list = new MyPolygons();
        list.removeFromTail();
    }

    @Test
    public void RemoveFromTail_PopulatedList_RemoveLastNode()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        Assert.assertEquals("Message3" ,list.removeFromTail());
    }

    @Test
    public void Clear_EmptyList_DoNothing()
    {
        MyPolygons list = new MyPolygons();
        list.clear();
        Assert.assertEquals(true, list.isEmpty());
    }

    @Test
    public void Clear_PopulatedList_RemoveUntilNothingRemains()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        list.clear();
        Assert.assertEquals(true, list.isEmpty());
    }

    @Test
    public void IsEmpty_EmptyList_ReturnTrue()
    {
        MyPolygons list = new MyPolygons();
        Assert.assertEquals(true, list.isEmpty());
    }

    @Test
    public void IsEmpty_PopulatedList_ReturnFalse()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        Assert.assertEquals(false, list.isEmpty());
    }

    @Test
    public void Step_EmptyList_StepToNextNode()
    {
        MyPolygons list = new MyPolygons();
        list.iteratorNext();
    }

    @Test
    public void Step_PopulatedList_StepToNextNode()
    {
        MyPolygons list = new MyPolygons();
        list.prepend("Message1");
        list.prepend("Message2");
        list.prepend("Message3");
        list.iteratorNext();
        Assert.assertEquals(0, list.getCurrentNodeIndex());
    }

    @Test
    public void GetCurrentNodeIndex_EmptyList_ReturnNegativeOne()
    {
        MyPolygons list = new MyPolygons();
        Assert.assertEquals(-1, list.getCurrentNodeIndex()); //Not in structure
    }

    @Test
    public void GetCurrentNodeIndex_PopulatedList_ReturnPositionOfCurrentNode()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        list.iteratorNext();
        list.iteratorNext();
        Assert.assertEquals(1,list.getCurrentNodeIndex());
    }

    @Test
    public void IteratorReset_EmptyList_ResetsToSentinel()
    {
        MyPolygons list = new MyPolygons();
        list.iteratorReset();
        Assert.assertEquals(-1, list.getCurrentNodeIndex());
    }

    @Test
    public void IteratorReset_PopulatedList_ResetToSentinel()
    {
        MyPolygons list = new MyPolygons();
        list.append("Message1");
        list.append("Message2");
        list.append("Message3");
        list.append("Message4");
        list.iteratorNext();
        list.iteratorNext();
        list.iteratorNext();
        list.iteratorReset();
        Assert.assertEquals(-1, list.getCurrentNodeIndex());
    }








}
