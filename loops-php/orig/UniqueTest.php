<?php

require_once __DIR__ . "/Unique.php";

/**
 * Created by IntelliJ IDEA.
 * User: acj
 * Date: 24.01.16
 * Time: 17:25
 */
class UniqueTest extends \PHPUnit_Framework_TestCase
{
    private $unique;

    function setUp()
    {
        $this->unique = new Unique();
    }

    function test_getUniqueItems_scribble()
    {
        $items = array(
            new MyEntity("id1"),
            new MyEntity("id2"),
            new MyEntity("id1"),
        );
        $this->assertEquals(3, sizeof($items));

        $uniqueItems = $this->unique->getUniqueItems($items);
        $this->assertEquals(2, sizeof($uniqueItems));
    }                 
    
    public function getUniqueItems_orig()
    {
        $items = array();
        $item = new MyEntity(1);
        $items[] = $item;

        $item = new MyEntity(2);
        $items[] = $item;

        $item = new MyEntity(3);
        $items[] = $item;

        $item = new MyEntity(3);
        $items[] = $item;

        $item = new MyEntity(4);
        $items[] = $item;

        $item = new MyEntity(4);
        $items[] = $item;

        $item = new MyEntity(4);
        $items[] = $item;
    
        $uniqueItems = $this->unique->getUniqueItems($items);
        $this->assertInternalType('array', $uniqueItems, 'Should be an array');
        $this->assertCount(4, $uniqueItems, 'Should contain 4 items');
        $uniqueItem = $uniqueItems[0];
        $this->assertInternalType('object', $uniqueItem, 'Should be an object');
        $this->assertInstanceOf(
            MyEntity::class,
            $uniqueItem,
            'Should be an instance of MyEntity'
        );
        $this->assertSame(1, $uniqueItem->getId(), 'Should have given ID');
    
        $uniqueItem = $uniqueItems[1];
        $this->assertInternalType('object', $uniqueItem, 'Should be an object');
        $this->assertInstanceOf(
            MyEntity::class,
            $uniqueItem,
            'Should be an instance of MyEntity'
        );
        $this->assertSame(2, $uniqueItem->getId(), 'Should have given ID');
    
        $uniqueItem = $uniqueItems[2];
        $this->assertInternalType('object', $uniqueItem, 'Should be an object');
        $this->assertInstanceOf(
            MyEntity::class,
            $uniqueItem,
            'Should be an instance of MyEntity'
        );
        $this->assertSame(3, $uniqueItem->getId(), 'Should have given ID');
    
        $uniqueItem = $uniqueItems[3];
        $this->assertInternalType('object', $uniqueItem, 'Should be an object');
        $this->assertInstanceOf(
            MyEntity::class,
            $uniqueItem,
            'Should be an instance of MyEntity'
        );
        $this->assertSame(4, $uniqueItem->getId(), 'Should have given ID');
    }
}