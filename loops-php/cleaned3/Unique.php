<?php

/**
 * User: andreas.czakaj
 * Date: 24.01.16
 */
class Unique
{
    private $ids;
    private $uniqueItems;

    /**
     * Unique constructor.
     */
    public function __construct()
    {
        $this->ids = array();
        $this->uniqueItems = array();
    }


    function count()
    {
        return sizeof($this->ids);
    }


    function get($index)
    {
        return $this->uniqueItems[$index];
    }


    function addAll($items)
    {
        if ($items != null) {
            foreach ($items as $item) {
                $this->processUniqueItem($item);
            }
        }
    }

    function processUniqueItem($item)
    {
        if ($item instanceof IdentifiableInterface) {
            $itemId = $item->getId();

            if ($itemId != null && !isset($this->ids[$itemId])) {
                $this->ids[$itemId] = true;
                $this->uniqueItems[] = $item;
            }
        }
    }

}

interface IdentifiableInterface
{
    function getId();
}

class MyEntity implements IdentifiableInterface
{
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