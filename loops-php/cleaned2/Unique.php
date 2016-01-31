<?php

/**
 * User: andreas.czakaj
 * Date: 24.01.16
 */
class Unique
{
    function getUniqueItems($items)
    {
        $ids = array();
        $uniqueItems = array();

        if ($items != null) {
            foreach ($items as $item) {
                $this->processUniqueItem($item, $ids, $uniqueItems);
            }
        }

        return $uniqueItems;
    }

    function processUniqueItem($item, &$ids, &$uniqueItems)
    {
        if ($item instanceof IdentifiableInterface) {
            $itemId = $item->getId();

            if ($itemId != null && !isset($ids[$itemId])) {
                $ids[$itemId] = true;
                $uniqueItems[] = $item;
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