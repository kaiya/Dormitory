<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar "></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand glyphicon glyphicon-home" href="index.jsp">
			</a> <a class="navbar-brand glyphicon glyphicon-dashboard"
				href="index.jsp"> </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<li class="dropdown"><a href="/Dashboard/building/view.jsp"
					class="dropdown-toggle" role="button" aria-haspopup="true"
					aria-expanded="false">Building</a></li>

				<li class="dropdown"><a href="/Dashboard/dorm/view.jsp"
					class="dropdown-toggle" role="button" aria-haspopup="true"
					aria-expanded="false">Dormitory</a></li>

				<li class="dropdown"><a href="/Dashboard/stu/view.jsp"
					class="dropdown-toggle" role="button" aria-haspopup="true"
					aria-expanded="false">Student</a></li>
					
					<li class="dropdown"><a href="/Dashboard/dc/view.jsp"
					class="dropdown-toggle" role="button" aria-haspopup="true"
					aria-expanded="false">Dormitory Candidate</a></li>
			</ul>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<ul class="nav navbar-nav navbar-right" style="margin-right: 10px">
				<li><a href="#">${sessionScope.user}</a></li>
				<li class="dropdown"><a href="#"
					class="dropdown-toggle glyphicon glyphicon-user"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#" class="glyphicon glyphicon-off"
							id="logout_link"> Logout </a></li>
					</ul></li>
				<li><a href="#" class="glyphicon glyphicon-info-sign"> </a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
