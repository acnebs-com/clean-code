<?php

require_once __DIR__ . "/Unique.php";

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

        $this->unique->addAll($items);
        $this->assertEquals(2, $this->unique->count());
    }                 

    public function test_getUniqueItems_null_array()
    {
        $this->unique->addAll(null);
        $this->assertEquals(0, $this->unique->count(), "It should yield an empty array when the input is null");
    }

    public function test_getUniqueItems_empty_array()
    {
        $this->unique->addAll(array());
        $this->assertEquals(0, $this->unique->count(), "It should yield an empty array when the input is empty");
    }

    public function test_getUniqueItems_array_of_1()
    {
        $this->unique->addAll(
            array(
                new MyEntity(1),
            )
        );
        $this->assertEquals(1, $this->unique->count(), "It should process an array of 1 and yield 2 item in the result");
        $this->assertEquals(1, $this->unique->get(0)->getId());
    }

    public function test_getUniqueItems_array_of_2()
    {
        $this->unique->addAll(
            array(
                new MyEntity(1),
                new MyEntity(2),
            )
        );
        $this->assertEquals(2, $this->unique->count(), "It should process an array of 2 and yield 2 items in the result");
        $this->assertEquals(1, $this->unique->get(0)->getId());
        $this->assertEquals(2, $this->unique->get(1)->getId());
    }

    public function test_getUniqueItems_array_of_3_with_1_duplicate()
    {
        $this->unique->addAll(
            array(
                new MyEntity(1),
                new MyEntity(2),
                new MyEntity(1),
            )
        );
        $this->assertEquals(2, $this->unique->count(), "It should process an array of 3 and yield 2 items in the result because there's 1 duplicate");
        $this->assertEquals(1, $this->unique->get(0)->getId());
        $this->assertEquals(2, $this->unique->get(1)->getId());
    }


    public function test_processUniqueItem_wrong_type()
    {
        $this->unique->processUniqueItem(
            new MyWrongEntity(23)
        );
        $this->assertEquals(0, $this->unique->count(), "It should not process items that do not implement IdentifiableInterface");
    }

    public function test_processUniqueItem_item_null()
    {
        $this->unique->processUniqueItem(
            null
        );
        $this->assertEquals(0, $this->unique->count(), "It should not process items that are null");
    }

    public function test_processUniqueItem_id_null()
    {
        $this->unique->processUniqueItem(
            new MyEntity(null)
        );
        $this->assertEquals(0, $this->unique->count(), "It should not process items whose ID is null");
    }

    public function test_processUniqueItem_OK()
    {
        $this->unique->processUniqueItem(
            new MyEntity(1)
        );
        $this->assertEquals(1, $this->unique->count(), "It should yield a valid item -> 1 item should be in list");

        $this->unique->processUniqueItem(
            new MyEntity(2)
        );
        $this->assertEquals(
            2, $this->unique->count(),
            "It should yield a valid item -> 2 items should be in list because the second item has a different ID"
        );

        $this->unique->processUniqueItem(
            new MyEntity(1)
        );
        $this->assertEquals(
            2, $this->unique->count(),
            "If should ignore the 3rd item because its ID is the same as 1st one's"
        );
    }
}

class MyWrongEntity {
    private $id;

    public function __construct($id)
    {
        $this->id = $id;
    }


    function getId()
    {
        return $this->id;
    }
}