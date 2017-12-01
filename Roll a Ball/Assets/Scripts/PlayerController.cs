using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {
	public float speed;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void FixedUpdate(){
		float moveHorizontal = Input.GetAxis ("Horizontal");
		float moveVertical = Input.GetAxis ("Vertical");
		Debug.Log ("(" + moveHorizontal + ", " + moveVertical + ")\n");
		Roll (moveHorizontal, moveVertical);
	}

	void Roll(float moveHorizontal, float moveVertical){
		Vector3 movement = new Vector3 (moveHorizontal, 0.0f, moveVertical);
		GetComponent<Rigidbody> ().AddForce (movement * speed * Time.deltaTime);		
	}

	void ARoll(string paramString){
		string[] paramArray = paramString.Split('#');
		if(paramArray.Length != 2)
			return;
		float moveHorizontal = float.Parse (paramArray [0]);
		float moveVertical = float.Parse(paramArray[1]);
		Roll(moveHorizontal, moveVertical);
	}
}
