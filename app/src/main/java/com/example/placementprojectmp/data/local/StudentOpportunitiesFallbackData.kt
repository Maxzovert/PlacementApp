package com.example.placementprojectmp.data.local

import com.example.placementprojectmp.R
import com.example.placementprojectmp.viewmodel.DriveUiModel
import com.example.placementprojectmp.viewmodel.Industry
import com.example.placementprojectmp.viewmodel.JobDepartment
import com.example.placementprojectmp.viewmodel.JobType
import com.example.placementprojectmp.viewmodel.JobUiModel
import com.example.placementprojectmp.viewmodel.Status
import com.example.placementprojectmp.viewmodel.WorkMode
import java.time.LocalDate

/** Static fallback lists for student opportunities when API is empty or fails. */
object StudentOpportunitiesFallbackData {

    val jobs: List<JobUiModel> = listOf(
        JobUiModel(
            id = "opp-j1",
            companyLogoResId = R.drawable.comp_1,
            companyName = "Nexora Systems",
            location = "Bengaluru, India",
            jobRole = "Associate Android Engineer",
            department = JobDepartment.TECH,
            jobType = JobType.FULL_TIME,
            industry = Industry.TECH,
            workMode = WorkMode.HYBRID,
            salaryLpa = 12.5f,
            status = Status.OPEN,
            lastDate = LocalDate.of(2026, 8, 31),
            appliedCount = 120
        ),
        JobUiModel(
            id = "opp-j2",
            companyLogoResId = R.drawable.comp_1,
            companyName = "FinEdge Analytics",
            location = "Mumbai, India",
            jobRole = "Data Analyst Intern",
            department = JobDepartment.TECH,
            jobType = JobType.INTERNSHIP,
            industry = Industry.FINANCE,
            workMode = WorkMode.REMOTE,
            salaryLpa = 4.2f,
            status = Status.OPEN,
            lastDate = LocalDate.of(2026, 9, 15),
            appliedCount = 85
        ),
        JobUiModel(
            id = "opp-j3",
            companyLogoResId = R.drawable.comp_1,
            companyName = "MediCore Labs",
            location = "Hyderabad, India",
            jobRole = "Product Designer",
            department = JobDepartment.MANAGEMENT,
            jobType = JobType.FULL_TIME,
            industry = Industry.HEALTHCARE,
            workMode = WorkMode.ONSITE,
            salaryLpa = 9.0f,
            status = Status.UPCOMING,
            lastDate = LocalDate.of(2026, 10, 10),
            appliedCount = 40
        ),
        JobUiModel(
            id = "opp-j4",
            companyLogoResId = R.drawable.comp_1,
            companyName = "EduSphere",
            location = "Pune, India",
            jobRole = "Backend Engineer (Part-time)",
            department = JobDepartment.TECH,
            jobType = JobType.CONTRACT,
            industry = Industry.EDUCATION,
            workMode = WorkMode.HYBRID,
            salaryLpa = 6.5f,
            status = Status.OPEN,
            lastDate = LocalDate.of(2026, 8, 20),
            appliedCount = 62
        )
    )

    val drives: List<DriveUiModel> = listOf(
        DriveUiModel(
            id = "opp-d1",
            companyLogoResId = R.drawable.comp_1,
            companyName = "Nexora Systems",
            driveName = "Campus Hiring 2025 – Engineering",
            startDate = LocalDate.of(2026, 8, 1),
            lastDateToRegister = LocalDate.of(2026, 8, 30),
            status = Status.OPEN,
            candidateCount = 240
        ),
        DriveUiModel(
            id = "opp-d2",
            companyLogoResId = R.drawable.comp_1,
            companyName = "FinEdge Analytics",
            driveName = "Finance & Analytics Drive",
            startDate = LocalDate.of(2026, 9, 5),
            lastDateToRegister = LocalDate.of(2026, 9, 25),
            status = Status.UPCOMING,
            candidateCount = 180
        ),
        DriveUiModel(
            id = "opp-d3",
            companyLogoResId = R.drawable.comp_1,
            companyName = "MediCore Labs",
            driveName = "Healthcare Tech Internship Drive",
            startDate = LocalDate.of(2026, 7, 15),
            lastDateToRegister = LocalDate.of(2026, 7, 28),
            status = Status.CLOSED,
            candidateCount = 95
        )
    )

    fun websiteUrlForCompany(companyName: String): String? {
        val normalized = companyName.trim()
        if (normalized.isBlank()) return null
        return when (normalized) {
            "Nexora Systems" -> "https://www.nexora.systems"
            "FinEdge Analytics" -> "https://www.finedgeanalytics.com"
            "MediCore Labs" -> "https://www.medicorelabs.com"
            "EduSphere" -> "https://www.edusphere.com"
            else -> {
                val slug = normalized
                    .lowercase()
                    .replace(Regex("[^a-z0-9]+"), "")
                    .ifBlank { return null }
                "https://www.$slug.com"
            }
        }
    }

    fun websiteUrlForJob(jobId: String): String? {
        val companyName = OpportunitiesCatalogHolder.jobs.firstOrNull { it.id == jobId }?.companyName
            ?: jobs.firstOrNull { it.id == jobId }?.companyName
            ?: return null
        return websiteUrlForCompany(companyName)
    }

    fun websiteDisplayForUrl(websiteUrl: String): String {
        val stripped = websiteUrl.removePrefix("https://").removePrefix("http://")
        return if (stripped.startsWith("www.")) stripped else "www.$stripped"
    }

    fun registrationUrl(driveId: String): String {
        val fromCatalog = OpportunitiesCatalogHolder.drives.firstOrNull { it.id == driveId }?.companyName
        val fromStatic = drives.firstOrNull { it.id == driveId }?.companyName
        val companyName = (fromCatalog ?: fromStatic).orEmpty()
        val companySlug = companyName
            .lowercase()
            .replace(Regex("[^a-z0-9]+"), "")
            .ifBlank { "placement" }
        return "https://www.$companySlug.com"
    }
}
