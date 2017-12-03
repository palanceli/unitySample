using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {
	public GameObject player;	// 摄像机跟随谁运动
	private Vector3 offset;		// 摄像机初始位置

	// Use this for initialization
	void Start () {
		offset = transform.position;	// 记录初始位置
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void LateUpdate(){
		// 摄像机的位置=球体位置+初始偏移位置
		transform.position = player.transform.position + offset;
	}
}
