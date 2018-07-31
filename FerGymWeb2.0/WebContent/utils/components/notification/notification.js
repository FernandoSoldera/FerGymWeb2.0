function createNotification(title, content, type, time)
{
	var color = "";
	var icon = "";
	
	if(type == "success") { color = '#28a745'; icon = "check"; }
	else if(type == "error") { color = '#dc3545'; icon = "times";}
	else if(type == "warning") { color = '#ffc107'; icon = "exclamation-triangle";}
	else { color = '#17a2b8'; icon = "info-circle";}
	
	var notification = 	$('<div class="divNotification animated bounceInRight" style="position: absolute; top: 15px; right: 15px; z-index: 100; box-shadow: 1px 1px 3px black; width: 350px; height: 100px; border-radius: 20px; background-color: '+ color +'; display: flex;">' +
							'<div style="width: 25%; display: flex; justify-content: center; align-items: center;">' +
								'<i class="fas fa-'+ icon +'" style="color: white; font-size: 3em;"></i>' +
							'</div>' +
							'<div style="width: 75%; display: flex; flex-direction: column;">' +
								'<div style="width: 100%; height:40%; display: flex; justify-content: flex-start; align-items: center;">' +
									'<span style="color: white; font-size: 21px;">'+ title +'</span>' +
								'</div>' +
								'<div style="color: white; width: 100%; height:60%; display: flex; justify-content: flex-start; align-items: flex-start;">' +
									'<span strle="word-break: break-word; font-size: 15px;">'+ content +'</span>' +
								'</div>' +
							'</div>' +
						'</div>');
	
	$("body").append(notification);
	
	setTimeout(function(){ notification.addClass("bounceOutRight"); }, time);
	setTimeout(function(){ notification.remove(); }, time+1000);
}