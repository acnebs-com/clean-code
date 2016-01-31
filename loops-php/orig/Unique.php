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

        foreach ($items as $item) {
            if (!($item instanceof IdentifiableInterface)) {
                continue;
            }

            $itemId = $item->getId();

            if (!isset($ids[$itemId])) {
                $ids[$itemId] = true;
                $uniqueItems[] = $item;
            }
        }

        return $uniqueItems;
    }

}

interface IdentifiableInterface
{
    function getId();
}

class MyEntity implements IdentifiableInterface
{
    private $id;

    /**
     * MyEntity constructor.
     * @param $id
     */
    public function __construct($id)
    {
        $this->id = $id;
    }


    function getId()
    {
        return $this->id;
    }
}