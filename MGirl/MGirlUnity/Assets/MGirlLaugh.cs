using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MGirlLaugh : MonoBehaviour {
	public Animation mAnimation;

	// Use this for initialization
	void Start () {
		
	}

	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.G)) {
			Debug.Log ("key down G");
			StartCoroutine ("FireLaugh");
		}
	}

	IEnumerator FireLaugh(){
		Debug.Log ("FireLaugh...");
		mAnimation = GetComponent<Animation> ();
		mAnimation.Play (mAnimation.clip.name);
		Debug.Log (mAnimation.clip.name);
		yield return new WaitForSeconds (mAnimation.clip.length);
	}
}
