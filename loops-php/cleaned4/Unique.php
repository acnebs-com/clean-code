<?php

interface Collection
{
    function count();
    function getByIndex($index);
    function addAll($collection);
}

interface ListConsumer {
    function accept($item);
}

interface ListCollection extends Collection {
    function add($item);
    function stream(ListConsumer $consumer);
}

class ArrayListCollection implements ListCollection
{
    private $items;

    /**
     * ListCollection constructor.
     */
    public function __construct()
    {
        $this->items = array();
    }

    function add($item)
    {
        $this->items[] = $item;
    }


    function count()
    {
        return sizeof($this->items);
    }

    function getByIndex($index)
    {
        return $this->items[$index];
    }

    function addAll($collection)
    {
        if ($collection != null) {
            foreach($collection as $item) {
                $this->add($item);
            }
        }
    }

    function stream(ListConsumer $consumer)
    {
        foreach ($this->items as $item) {
            $consumer->accept($item);
        }
    }
}

abstract class AbstractListCollectionDecorator implements ListCollection
{
    private $delegate;

    public function __construct($delegate)
    {
        if ($delegate == null) {
            throw new \Exception("delegate must not be null");
        } else {
            $this->delegate = $delegate;
        }
    }

    /**
     * @return mixed
     */
    protected function getDelegate()
    {
        return $this->delegate;
    }


    function count()
    {
        return $this->delegate->count();
    }

    function getByIndex($index)
    {
        return $this->delegate->getByIndex($index);
    }

    function addAll($collection)
    {
        $this->delegate->addAll($collection);
    }

    function add($item)
    {
        $this->delegate->add($item);
    }

    function stream(ListConsumer $consumer)
    {
        $this->delegate->stream($consumer);
    }
}


class UniqueListCollection extends AbstractListCollectionDecorator implements ListCollection
{

    private $ids;
    private $getIdFunc;

    /**
     * UniqueListCollection constructor.
     */
    public function __construct($getIdFunc)
    {
        parent::__construct(new ArrayListCollection());
        $this->getIdFunc = $getIdFunc;
    }

    function addAll($collection)
    {
        if ($collection != null) {
            foreach($collection as $item) {
                $this->add($item);
            }
        }
    }


    function add($item)
    {
        if ($item != null) {
            $itemId = call_user_func($this->getIdFunc, $item);

            if ($itemId != null && !isset($this->ids[$itemId])) {
                $this->ids[$itemId] = true;
                $this->getDelegate()->add($item);
            }
        }
    }
}


interface MapConsumer {
    function accept($key, $item);
}

interface MapCollection extends Collection {
    function add($key, $item);
    function getByKey($key);
    function stream(MapConsumer $consumer);
}


class ArrayMapCollection implements MapCollection
{
    private $items;

    /**
     * ListCollection constructor.
     */
    public function __construct()
    {
        $this->items = array();
    }

    function add($key, $item)
    {
        $this->items[$key] = $item;
    }


    function count()
    {
        return sizeof($this->items);
    }

    function getByIndex($index)
    {
        return $this->items[$index];
    }

    function getByKey($key)
    {
        return $this->items[$key];
    }


    function addAll($collection)
    {
        if ($collection != null) {
            foreach($collection as $key => $item) {
                $this->add($key, $item);
            }
        }
    }

    function stream(MapConsumer $consumer)
    {
        foreach ($this->items as $key => $item) {
            $consumer->accept($key, $item);
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