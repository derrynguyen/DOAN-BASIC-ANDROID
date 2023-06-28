<?php

class Model
{

	protected $connect;

	protected $database;

	protected $table;

	protected $resultQuery;



	// CONNECT DATABASE

	public function __construct($params = null)
	{

		if ($params == null) {

			$params['server'] = 'localhost';

			$params['username'] = 'root';

			$params['password'] = '';

			$params['database'] = 'goapp';

			$params['table'] = '';
		}



		$link = mysqli_connect($params['server'], $params['username'], $params['password'], $params['database']);

		mysqli_set_charset($link, "utf8");



		if ($link == false) {

			die("ERROR: Could not connect. " . mysqli_connect_error());
		} else {

			$this->connect = $link;

			$this->database = $params['database'];

			$this->table = $params['table'];

			//$this->setDatabase();

			//$this->query("SET NAMES 'UTF8'");

			//$this->query("SET CHARACTER SET 'UTF8'");

		}
	}



	// SET CONNECT

	public function setConnect($connect)
	{

		$this->connect = $connect;
	}



	// SET DATABASE

	public function setDatabase($database = null)
	{

		if ($database != null) {

			$this->database = $database;
		}

		@mysqli_select_db($this->database, $this->connect);
	}



	// SET TABLE

	public function setTable($table)
	{

		$this->table = $table;
	}



	// DISCONNECT DATABASE

	public function __destruct()
	{

		@mysqli_close($this->connect);
	}



	// INSERT

	public function insert($data, $type = 'single')
	{

		if ($type == 'single') {

			$newQuery = $this->createInsertSQL($data);

			$query = "INSERT INTO `$this->table`(" . $newQuery['cols'] . ") VALUES (" . $newQuery['vals'] . ")";

			//print_r($query); 

			$this->query($query);
		} else {

			foreach ($data as $value) {

				$newQuery = $this->createInsertSQL($value);

				$query = "INSERT INTO `$this->table`(" . $newQuery['cols'] . ") VALUES (" . $newQuery['vals'] . ")";



				$this->query($query);
			}
		}

		return $this->lastID();
	}



	// CREATE INSERT SQL

	public function createInsertSQL($data)
	{

		$newQuery = array();

		$cols = '';

		$vals = '';

		if (!empty($data)) {

			foreach ($data as $key => $value) {

				$cols .= ", `$key`";

				$vals .= ", '$value'";
			}
		}

		$newQuery['cols'] = substr($cols, 2);

		$newQuery['vals'] = substr($vals, 2);

		return $newQuery;
	}



	// LAST ID

	public function lastID()
	{

		return mysql_insert_id($this->connect);
	}



	// QUERY

	public function query($query)
	{

		$this->resultQuery = mysqli_query($this->connect, $query);

		return $this->resultQuery;
	}



	// UPDATE

	public function update($data, $where)
	{

		$newSet = $this->createUpdateSQL($data);

		//$newWhere 	= $this->createWhereUpdateSQL($where); die();

		$query = "UPDATE `$this->table` SET " . $newSet . " WHERE $where";

		$this->query($query);

		return $this->affectedRows();
	}



	// CREATE UPDATE SQL

	public function createUpdateSQL($data)
	{

		$newQuery = "";

		if (!empty($data)) {

			foreach ($data as $key => $value) {

				$newQuery .= ", `$key` = '$value'";
			}
		}

		$newQuery = substr($newQuery, 2);

		return $newQuery;
	}



	// CREATE WHERE UPDATE SQL

	public function createWhereUpdateSQL($data)
	{

		$newWhere = '';

		if (!empty($data)) {

			foreach ($data as $key => $value) {

				$newWhere[] = "`$key` = '$value'";

				//$newWhere[] = $value[2];



			}

			$newWhere = implode(" ", $newWhere);
		}

		return $newWhere;
	}



	// AFFECTED ROWS

	public function affectedRows()
	{

		return mysql_affected_rows($this->connect);
	}



	// DELETE

	public function delete($where)
	{

		$newWhere = $this->createWhereDeleteSQL($where);

		$query = "DELETE FROM `$this->table` WHERE `id` IN ($newWhere)";

		$this->query($query);

		return $this->affectedRows();
	}



	// CREATE WHERE DELTE SQL

	public function createWhereDeleteSQL($data)
	{

		$newWhere = '';

		if (!empty($data)) {

			foreach ($data as $id) {

				$newWhere .= "'" . $id . "', ";
			}

			$newWhere .= "'0'";
		}

		return $newWhere;
	}



	// LIST RECORD

	public function fetchAll($query)
	{

		$result = array();

		if (!empty($query)) {

			$resultQuery = $this->query($query);

			if (mysqli_num_rows($resultQuery) > 0) {

				while ($row = mysqli_fetch_assoc($resultQuery)) {

					$result[] = ($row);
				}

				mysqli_free_result($resultQuery);
			}
		}





		return ($result);

		//return json_encode($response);

	}



	public function fetchAll_strim($query)
	{

		$result = array();

		$response = array();

		$response["baiviet"] = array();

		if (!empty($query)) {

			$resultQuery = $this->query($query);

			if (mysql_num_rows($resultQuery) > 0) {

				while ($row = mysql_fetch_assoc($resultQuery)) {



					$result[] = array(

						'title' => $this->substrwords(htmlspecialchars_decode($row['title']), 80),

						'id_baiviet' => $row['id_baiviet'],

						'images' => $row['images'],

						'trang' => $row['trang'],

						'chuyen_muc' => $row['chuyen_muc']

					);
				}

				mysql_free_result($resultQuery);
			}
		}



		$response["baiviet"] = $result;

		$response["success"] = 1;

		//return ($response);

		return json_encode($response);
	}

	// LIST RECORD

	public function fetchPairs($query)
	{

		$result = array();

		if (!empty($query)) {

			$resultQuery = $this->query($query);

			if (mysql_num_rows($resultQuery) > 0) {

				while ($row = mysql_fetch_assoc($resultQuery)) {

					$result[$row['id']] = $row['name'];
				}

				mysql_free_result($resultQuery);
			}
		}

		return $result;
	}



	// SINGLE RECORD

	public function fetchRow($query)
	{



		$result = array();

		if (!empty($query)) {

			$resultQuery = $this->query($query);

			if (mysqli_num_rows($resultQuery) > 0) {

				$result = mysqli_fetch_assoc($resultQuery);
			}

			mysqli_free_result($resultQuery);
		}

		return $result;
	}



	// EXIST

	public function isExist($query)
	{

		if ($query != null) {

			$this->resultQuery = $this->query($query);
		}

		if (mysqli_num_rows($this->resultQuery) > 0)
			return true;

		return false;
	}



	public function substrwords($text, $maxchar, $end = '...')
	{

		if (strlen($text) > $maxchar || $text == '') {

			$words = preg_split('/\s/', $text);

			$output = '';

			$i = 0;

			while (1) {

				$length = strlen($output) + strlen($words[$i]);

				if ($length > $maxchar) {

					break;
				} else {

					$output .= " " . $words[$i];

					++$i;
				}
			}

			$output .= $end;
		} else {

			$output = $text;
		}

		return $output;
	}
}
